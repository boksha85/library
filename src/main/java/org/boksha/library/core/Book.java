package org.boksha.library.core;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Book {
    @JsonProperty
	private long id;
    
    @JsonProperty
	private String isbn;
	
    @JsonProperty
	private String title;
	
    @JsonProperty
	private String listOfAuthors;
		
    @JsonProperty
	private Integer numberOfPages;
	
    @JsonProperty
	private String genre;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(long id, String isbn, String title, String listOfAuthors, Integer numberOfPages, String genre) {
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

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }

        final Book that = (Book) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.isbn, that.isbn) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.listOfAuthors, that.listOfAuthors) &&
                Objects.equals(this.numberOfPages, that.numberOfPages) &&
                Objects.equals(this.genre, that.genre);
    }
	
	
	@Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, listOfAuthors, numberOfPages, genre);
    }
	
}
