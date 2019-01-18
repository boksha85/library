package org.boksha.library.core;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class Helpers {

	public static void verifyBook(Book book) {
		if (book == null) {
			throw new WebApplicationException(CustomizedErrorMessages.INVALID_BOOK, Status.BAD_REQUEST);
		}
		if (book.getIsbn() == null || book.getIsbn().equalsIgnoreCase("")) {
			throw new WebApplicationException(CustomizedErrorMessages.INVALID_ISBN, Status.BAD_REQUEST);
		}
		if (book.getTitle() == null || book.getIsbn().equalsIgnoreCase("")) {
			throw new WebApplicationException(CustomizedErrorMessages.INVALID_TITLE, Status.BAD_REQUEST);
		}
		if (book.getListOfAuthors() == null || book.getIsbn().equalsIgnoreCase("")) {
			throw new WebApplicationException(CustomizedErrorMessages.INVALID_LIST_OF_AUTHORS, Status.BAD_REQUEST);
		}
		if (book.getNumberOfPages() == null || book.getNumberOfPages() <= 0) {
			throw new WebApplicationException(CustomizedErrorMessages.INVALID_NUMBER_OF_PAGES, Status.BAD_REQUEST);
		}
		if (book.getGenre() == null || book.getGenre().equalsIgnoreCase("")) {
			throw new WebApplicationException(CustomizedErrorMessages.INVALID_GENRE, Status.BAD_REQUEST);
		}

	}

}
