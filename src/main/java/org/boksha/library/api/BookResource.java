package org.boksha.library.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.boksha.library.core.Book;
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
		List<Book> books= bookDAO.getAllBooks();
				
		return books;
	}
	@PUT
	@Path("/add")
	public void addBook(Book book) {
		bookDAO.insertBook(book.getISBN(), book.getTitle(), book.getListOfAuthors(), book.getNumberOfPages(), book.getGenre());
	}
	
	
}
