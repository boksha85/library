package org.boksha.library.core;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    @JsonProperty
	private long id;
	
	@NotEmpty
    @JsonProperty
	private String isbn;
	
	@NotEmpty
    @JsonProperty
	private String title;
	
	@NotEmpty
    @JsonProperty
	private String listOfAuthors;
	
	@NotNull	
    @JsonProperty
	private int numberOfPages;
	
	@NotEmpty
    @JsonProperty
	private String genre;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(long id, String isbn, String title, String listOfAuthors, int numberOfPages, String genre) {
		this.id = id;
		this.isbn = isbn;
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
