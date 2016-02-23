package com.starterkit.javafxlibrary.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

import com.starterkit.javafxlibrary.dataprovider.BookProvider;
import com.starterkit.javafxlibrary.dataprovider.data.BookTo;
import com.starterkit.javafxlibrary.model.BookListModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookListControler {
    
	@FXML
	TextField titleField;
	
	@FXML
	Button searchButton;
	
	@FXML
	TableView<BookTo> resultTable;
	
	private static final Logger LOG = Logger.getLogger(BookListControler.class);
	
	private Collection<BookTo> books = new ArrayList<>();
	
	private BookProvider bookProvider = BookProvider.INSTANCE;
	
	private final BookListModel model = new BookListModel();
	
	
	public BookListControler() {
		
	}
	
	@FXML
    private void initialize() {
		titleField.textProperty().bindBidirectional(model.titleProperty());
		resultTable.itemsProperty().bind(model.resultProperty());
		searchButton.disableProperty().bind(titleField.textProperty().isEmpty());
    }    
    
	@FXML
	public void searchButtonAction(ActionEvent event) {
		LOG.debug("'Search' button clicked");
		books = bookProvider.findBooks(titleField.getText());
	}
}
