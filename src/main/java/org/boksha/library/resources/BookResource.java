package org.boksha.library.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.boksha.library.core.Book;
import org.boksha.library.core.CustomizedErrorMessages;
import org.boksha.library.core.Helpers;
import org.boksha.library.db.BookDAO;

@Path("books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

	BookDAO bookDAO;

	public BookResource(BookDAO bookDao) {
		bookDAO = bookDao;
	}

	@GET
	public List<Book> getBooks() {
		List<Book> books = bookDAO.getAllBooks();

		return books;
	}

	@GET
	@Path("/lastfive")
	public List<Book> getLastFiveBooks() {
		List<Book> books = bookDAO.getLastFiveBooks();

		return books;
	}
	
	@POST
	@Path("/add")
	public void addBook(Book book) {
		// verify parameters
		Helpers.verifyBook(book);
		/* ISBN is unique The International Standard Book Number so we need to check is it already assigned to some book */
		Book bookInDb = bookDAO.getBookByISBN(book.getIsbn());
		if (bookInDb != null) {
			final String msg = String.format(CustomizedErrorMessages.BOOK_ALREADY_EXISTS_IN_LIBRARY, bookInDb.getIsbn());
			throw new WebApplicationException(msg, Status.BAD_REQUEST);
		}		
		bookDAO.insertBook(book);
	}
	
	

}
