package com.starterkit.javafxlibrary.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.starterkit.javafxlibrary.dataprovider.BookProvider;
import com.starterkit.javafxlibrary.dataprovider.data.AuthorTo;
import com.starterkit.javafxlibrary.dataprovider.data.BookTo;
import com.starterkit.javafxlibrary.model.NewBookModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewBookController {

	@FXML
	TextField titleField;
	
	@FXML
	TextField firstNameField;
	
	@FXML
	TextField lastNameField;
	
	@FXML
	Button addButton;
	
	@FXML
	Button cancelButton;
	
	private static final Logger LOG = Logger.getLogger(NewBookController.class);
	
	private BookProvider bookProvider = BookProvider.INSTANCE;
	
	private final NewBookModel model = new NewBookModel();
	
	public NewBookController(){
		
	}
	
	@FXML
	private void initialize(){
		titleField.textProperty().bindBidirectional(model.titleProperty());
		firstNameField.textProperty().bindBidirectional(model.firstNameProperty());
		lastNameField.textProperty().bindBidirectional(model.lastNameProperty());
		
		addButton.disableProperty().bind(titleField.textProperty().isEmpty());
		addButton.disableProperty().bind(firstNameField.textProperty().isEmpty());
		addButton.disableProperty().bind(lastNameField.textProperty().isEmpty());
	}
	
	@FXML
	public void cancelButtonAction(ActionEvent event) {
	    closeWindow();
	}
	
	@FXML
	public void addButtonAction(ActionEvent event) {
		sendPostRequest();
	    closeWindow();
	}

	private void closeWindow() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}
	
	private void sendPostRequest() {
		Runnable backgroundTask = new Runnable() {

			@Override
			public void run() {
				LOG.debug("backgroundTask.run() called");
				bookProvider.addBook(createBookJson());
			}
		};
		new Thread(backgroundTask).start();
	}
	
	private String createBookJson() {
		Gson jsonParser = new Gson();
		String jsonString;
		Set<AuthorTo> authors = new HashSet<>();
		authors.add(new AuthorTo(null, firstNameField.getText(), lastNameField.getText()));
		BookTo book = new BookTo(null, titleField.getText(), authors);

		jsonString = jsonParser.toJson(book);
		return jsonString;
	}
	
}
