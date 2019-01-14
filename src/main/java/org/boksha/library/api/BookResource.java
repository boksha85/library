package org.boksha.library.api;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

	@GET
	public List<Book> getBooks() {
		Book b = new Book();
		b.setISBN("1321-121");
		b.setGenre("Fantastic");
		b.setListOfAuthors("Gorge RR Martin");
		b.setTitle("Game Of Thrones");
		b.setNumberOfPages(700);
		return Collections.singletonList(b);
	}

}
