package com.starterkit.javafxlibrary.dataprovider.data;

public class AuthorTo {

	private Long id;
	private String firstName;
	private String lastName;
	
	public AuthorTo(){
		
	}
	
	public AuthorTo(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString(){
		return new StringBuffer("").append(this.firstName)
				.append(" ").append(this.lastName).toString();
	}
	
}
