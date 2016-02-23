package com.starterkit.javafxlibrary.dataprovider.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.starterkit.javafxlibrary.dataprovider.BookProvider;
import com.starterkit.javafxlibrary.dataprovider.data.BookTo;
import com.starterkit.javafxlibrary.rest.BookRestService;
import com.starterkit.javafxlibrary.rest.impl.BookRestServiceImpl;

public class BookProviderImpl implements BookProvider {

	private BookRestService bookRestService = new BookRestServiceImpl();
	
	private Collection<BookTo> books = new ArrayList<>();
	
	public BookProviderImpl() {
	}
	
	@Override
	public Collection<BookTo> findBooks(String titlePrefix) {
		return books = bookRestService.sendGET(titlePrefix);
	}

}
