package org.boksha.library.core;

import java.util.ArrayList;
import java.util.List;

public class MockData {
	
	public static Book getBook() {
		Book book = new Book(1, "123456789", "book1", "boksha", 100, "test");
		return book;
	}
	public static Book getBookWithEmptyIsbn() {
		Book book = new Book(1, "", "book1", "boksha", 100, "test");
		return book;
	}
	public static Book getBookWithNullIsbn() {
		Book book = new Book(1, null, "book1", "boksha", 100, "test");
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
	
	public static List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>(); 
		books.add(new Book(1, "123456789", "book1", "boksha", 100, "test"));
		books.add(new Book(2, "223456789", "book2", "boksha", 100, "test"));
		books.add(new Book(3, "323456789", "book3", "boksha", 100, "test"));
		
		return books;
	}
}
