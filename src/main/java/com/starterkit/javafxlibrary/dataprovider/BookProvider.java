package com.starterkit.javafxlibrary.dataprovider;

import java.util.Collection;

import com.starterkit.javafxlibrary.dataprovider.data.BookTo;
import com.starterkit.javafxlibrary.dataprovider.impl.BookProviderImpl;

public interface BookProvider {

	BookProvider INSTANCE = new BookProviderImpl();
	
	Collection<BookTo> findBooks(String titlePrefix);
	void deleteBook(Long id);
	void addBook(String jsonString);
	
}
