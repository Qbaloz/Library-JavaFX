package com.starterkit.javafxlibrary.dataprovider.impl;

import java.util.Collection;

import com.starterkit.javafxlibrary.dataprovider.BookProvider;
import com.starterkit.javafxlibrary.dataprovider.data.BookTo;
import com.starterkit.javafxlibrary.rest.BookRestService;
import com.starterkit.javafxlibrary.rest.impl.BookRestServiceImpl;

public class BookProviderImpl implements BookProvider {

	private BookRestService bookRestService = new BookRestServiceImpl();
	
	public BookProviderImpl() {
	}
	
	@Override
	public Collection<BookTo> findBooks(String titlePrefix) {
		return bookRestService.sendGET(titlePrefix);
	}

	@Override
	public void deleteBook(Long id) {
		bookRestService.sendDELETE(id);
	}

	@Override
	public void addBook(String jsonString) {
		bookRestService.sendPOST(jsonString);
	}

}
