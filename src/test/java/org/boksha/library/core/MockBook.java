package org.boksha.library.core;

public class MockBook {
	
	public static Book getBook() {
		Book book = new Book(1, "123456789", "book1", "boksha", 100, "test");
		return book;
	}
	public static Book getBookWithEmptyGenre() {
		Book book = new Book(1, "123456789", "book1", "boksha", 100, "");
		return book;
	}

}
