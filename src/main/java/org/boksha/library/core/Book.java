package org.boksha.library.core;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
	@NotNull
    @JsonProperty
	private long id;
	@NotNull
    @JsonProperty
	private String ISBN;
	@NotNull
    @JsonProperty
	private String title;
	@NotNull
    @JsonProperty
	private String listOfAuthors;
	@NotNull
    @JsonProperty
	private int numberOfPages;
	@NotNull
    @JsonProperty
	private String genre;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(long id, String ISBN, String title, String listOfAuthors, int numberOfPages, String genre) {
		this.id = id;
		this.ISBN = ISBN;
		this.title = title;
		this.listOfAuthors = listOfAuthors;
		this.numberOfPages = numberOfPages;
		this.genre = genre;		
	}

		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getListOfAuthors() {
		return listOfAuthors;
	}
	public void setListOfAuthors(String listOfAuthors) {
		this.listOfAuthors = listOfAuthors;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
}
