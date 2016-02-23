package com.starterkit.javafxlibrary.model;

import java.util.ArrayList;
import java.util.List;

import com.starterkit.javafxlibrary.dataprovider.data.BookTo;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class BookListModel {

	private final StringProperty title = new SimpleStringProperty();
	private final ListProperty<BookTo> result = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<BookTo>()));

	public final String getTitle() {
		return title.get();
	}

	public final void setTitle(String value) {
		title.set(value);
	}

	public StringProperty titleProperty() {
		return title;
	}

	public final List<BookTo> getResult() {
		return result.get();
	}

	public final void setResult(List<BookTo> value) {
		result.setAll(value);
	}

	public ListProperty<BookTo> resultProperty() {
		return result;
	}

}
