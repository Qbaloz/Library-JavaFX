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
	private final StringProperty titleLabel = new SimpleStringProperty();
	private final StringProperty authorsLabel = new SimpleStringProperty();

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
	
	public final String getTitleLabel() {
		return titleLabel.get();
	}

	public final void setTitleLabel(String value) {
		titleLabel.set(value);
	}

	public StringProperty titleLabelProperty() {
		return titleLabel;
	}
	
	public final String getAuthorsLabel() {
		return authorsLabel.get();
	}

	public final void setAuthorsLabel(String value) {
		authorsLabel.set(value);
	}

	public StringProperty authorsLabelProperty() {
		return authorsLabel;
	}

}
