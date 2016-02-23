package com.starterkit.javafxlibrary.rest;

import java.util.List;

import com.starterkit.javafxlibrary.dataprovider.data.BookTo;

public interface BookRestService {

	public BookTo sendPOST(String jsonString);
	public List<BookTo> sendGET(String titlePrefix);
	public void sendDELETE(Long id);
	
}
