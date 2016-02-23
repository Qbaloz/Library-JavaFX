package com.starterkit.javafxlibrary.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

import com.starterkit.javafxlibrary.dataprovider.BookProvider;
import com.starterkit.javafxlibrary.dataprovider.data.BookTo;
import com.starterkit.javafxlibrary.model.BookListModel;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookListControler {

	@FXML
	TextField titleField;

	@FXML
	Button searchButton;

	@FXML
	TableView<BookTo> resultTable;

	@FXML
	TableColumn<BookTo, Number> idColumn;

	@FXML
	TableColumn<BookTo, String> titleColumn;

	private static final Logger LOG = Logger.getLogger(BookListControler.class);

	private BookProvider bookProvider = BookProvider.INSTANCE;

	private final BookListModel model = new BookListModel();

	public BookListControler() {

	}

	@FXML
	private void initialize() {

		initializeResultTable();
		titleField.textProperty().bindBidirectional(model.titleProperty());
		resultTable.itemsProperty().bind(model.resultProperty());
		
		model.setTitle("");
	}

	private void initializeResultTable() {
		idColumn.setCellValueFactory(cellData -> new ReadOnlyLongWrapper(cellData.getValue().getId()));
		titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
	}

	@FXML
	public void searchButtonAction(ActionEvent event) {
		LOG.debug("'Search' button clicked");
		Runnable backgroundTask = new Runnable() {

			@Override
			public void run() {
				LOG.debug("backgroundTask.run() called");

				Collection<BookTo> result = bookProvider.findBooks(model.getTitle());

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						LOG.debug("Platform.runLater(Runnable.run()) called");
						model.setResult(new ArrayList<BookTo>(result));
						resultTable.getSortOrder().clear();
					}
				});
			}
		};
		new Thread(backgroundTask).start();
	}
	
}
