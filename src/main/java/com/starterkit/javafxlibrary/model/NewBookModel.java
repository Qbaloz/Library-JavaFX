package com.starterkit.javafxlibrary.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NewBookModel {

	private final StringProperty title = new SimpleStringProperty();
	private final StringProperty firstName = new SimpleStringProperty();
	private final StringProperty lastName = new SimpleStringProperty();
	
	public final String getTitle() {
		return title.get();
	}

	public final void setTitle(String value) {
		title.set(value);
	}

	public StringProperty titleProperty() {
		return title;
	}
	
	public final String getFirstName() {
		return firstName.get();
	}

	public final void setFirstName(String value) {
		firstName.set(value);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	public final String getLastName() {
		return lastName.get();
	}

	public final void setLastName(String value) {
		lastName.set(value);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}
	
}
