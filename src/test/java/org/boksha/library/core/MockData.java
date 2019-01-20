package org.boksha.library.core;

import java.util.ArrayList;
import java.util.List;

public class MockData {

	public static Book getBook() {
		Book book = new Book(1, "123456789", "book1", "boksha", 100, "test");
		return book;
	}
	
	public static List<Book> getBookByTitle(){
		List<Book> books = new ArrayList<Book>(); 
		books.add(new Book(1, "123456789", "book1", "boksha", 100, "test"));
		return books;
	}
	public static Book getBookWithEmptyIsbn() {
		Book book = new Book(1, "", "book1", "boksha", 100, "test");
		return book;
	}
	public static Book getBookWithNullIsbn() {
		Book book = new Book(1, null, "book1", "boksha", 100, "test");
		return book;
	}	
	public static Book getBookWithEmptyTitle() {
		Book book = new Book(1, "123456789", "", "boksha", 100, "test");
		return book;
	}
	public static Book getBookWithNullTitle() {
		Book book = new Book(1, "123456789", null, "boksha", 100, "test");
		return book;
	}
	public static Book getBookWithEmptyListOfAuthors() {
		Book book = new Book(1, "123456789", "book1", "", 100, "test");
		return book;
	}
	public static Book getBookWithNullListOfAuthors() {
		Book book = new Book(1, "123456789", "book1", null, 100, "test");
		return book;
	}
	public static Book getBookWithZeroPages() {
		Book book = new Book(1, "123456789", "book1", "boksha", 0, "test");
		return book;
	}
	public static Book getBookWithMinusPages() {
		Book book = new Book(1, "123456789", "book1", "boksha", -1, "test");
		return book;
	}
	public static Book getBookWithNullPages() {
		Book book = new Book(1, "123456789", "book1", "boksha", null, "test");
		return book;
	}
	public static Book getBookWithEmptyGenre() {
		Book book = new Book(1, "123456789", "book1", "boksha", 100, "");
		return book;
	}
	public static Book getBookWithNullGenre() {
		Book book = new Book(1, "123456789", "book1", "boksha", 100, null);
		return book;
	}
	
	public static List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>(); 
		books.add(new Book(1, "123456789", "book1", "boksha", 100, "test"));
		books.add(new Book(2, "223456789", "book2", "boksha", 100, "test"));
		books.add(new Book(3, "323456789", "book3", "boksha", 100, "test"));
		books.add(new Book(4, "423456789", "book4", "boksha", 100, "test"));
		books.add(new Book(5, "523456789", "book5", "boksha", 100, "test"));
		
		return books;
	}
}
