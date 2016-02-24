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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BookListController {

	@FXML
	TextField titleField;
	
	@FXML
	Label titleLabel;
	
	@FXML
	Label authorsLabel;

	@FXML
	Button searchButton;

	@FXML
	Button deleteButton;

	@FXML
	Button addButton;

	@FXML
	TableView<BookTo> resultTable;

	@FXML
	TableColumn<BookTo, Number> idColumn;

	@FXML
	TableColumn<BookTo, String> titleColumn;

	private static final Logger LOG = Logger.getLogger(BookListController.class);

	private BookProvider bookProvider = BookProvider.INSTANCE;

	private final BookListModel model = new BookListModel();

	public BookListController() {

	}

	@FXML
	private void initialize() {

		initializeResultTable();
		titleField.textProperty().bindBidirectional(model.titleProperty());
		resultTable.itemsProperty().bind(model.resultProperty());
		titleLabel.textProperty().bindBidirectional(model.titleLabelProperty());
		authorsLabel.textProperty().bindBidirectional(model.authorsLabelProperty());

		model.setTitle("");
	}

	private void initializeResultTable() {
		idColumn.setCellValueFactory(cellData -> new ReadOnlyLongWrapper(cellData.getValue().getId()));
		titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle())); 
		
		updateBookDetails();
	}

	private void updateBookDetails() {
		resultTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookTo>() {

			@Override
			public void changed(ObservableValue<? extends BookTo> observable, BookTo oldValue, BookTo newValue) {
				LOG.debug(newValue + " selected");
				Platform.runLater(new Runnable() {
					  public void run() {
						if(newValue != null){
						    titleLabel.setText(newValue.getTitle());
						    authorsLabel.setText(newValue.getAuthors().toString());
						}
					  }
					});
			}
		});
	}

	@FXML
	public void searchButtonAction(ActionEvent event) {
		LOG.debug("'Search' button clicked");
		sendGetRequest();
	}

	@FXML
	public void deleteButtonAction(ActionEvent event) {
		BookTo book = resultTable.getSelectionModel().getSelectedItem();
		if(book != null){
			sendDeleteRequest(book.getId());
		}
	}

	@FXML
	public void addButtonAction(ActionEvent event) {
		openNewBookDialog();
	}

	private void openNewBookDialog() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/newbook.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			root.getStylesheets().add("/styles/darktheme.css");
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initOwner(resultTable.getScene().getWindow());
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setTitle("Add new book");
			stage.showAndWait();
			sendGetRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendGetRequest() {
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

	private void sendDeleteRequest(long id) {
		Runnable backgroundTask = new Runnable() {

			@Override
			public void run() {
				LOG.debug("backgroundTask.run() called");
				bookProvider.deleteBook(id);
				sendGetRequest();
			}
		};
		new Thread(backgroundTask).start();
	}

}
