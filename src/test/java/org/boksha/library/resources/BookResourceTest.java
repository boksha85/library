package org.boksha.library.resources;

import org.boksha.library.core.Book;
import org.boksha.library.core.CustomizedErrorMessages;
import org.boksha.library.core.MockData;
import org.boksha.library.core.ParseResponse;
import org.boksha.library.db.BookDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.dropwizard.testing.junit.ResourceTestRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Unit tests for @link BookResource}.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookResourceTest {
	private static final BookDAO BOOK_DAO = mock(BookDAO.class);
	private Book book;	
	private Book bookWithoutIsbn;
	private Book bookWithNullIsbn;
	private Book bookWithoutTitle;
	private Book bookWithNullTitle;
	private Book bookWithoutListOfAuthors;
	private Book bookWithNullListOfAuthors;
	private Book bookWithZeroPages;
	private Book bookWithMinusPages;
	private Book bookWithNullPages;
	private Book bookWithoutGenre;
	private Book bookWithNullGenre;
	
	private List<Book> books;
	private List<Book> booksByTitle;

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new BookResource(BOOK_DAO))
			.build();

	@Before
	public void setUp() {
		books = MockData.getBooks();
		booksByTitle = MockData.getBookByTitle();
		book = MockData.getBook();
		bookWithoutIsbn  = MockData.getBookWithEmptyIsbn();
		bookWithNullIsbn  = MockData.getBookWithNullIsbn();
		bookWithoutTitle  = MockData.getBookWithEmptyTitle();
		bookWithNullTitle  = MockData.getBookWithNullTitle();
		bookWithoutListOfAuthors  = MockData.getBookWithEmptyListOfAuthors();
		bookWithNullListOfAuthors  = MockData.getBookWithNullListOfAuthors();
		bookWithZeroPages = MockData.getBookWithZeroPages();
		bookWithMinusPages = MockData.getBookWithMinusPages();
		bookWithNullPages = MockData.getBookWithNullPages();
		bookWithoutGenre  = MockData.getBookWithEmptyGenre();
		bookWithNullGenre  = MockData.getBookWithNullGenre();
	}

	@After
	public void tearDown() {
		reset(BOOK_DAO);
	}

	@Test
	public void getBook() {
		when(BOOK_DAO.getInformationAboutBook("%" + book.getTitle() + "%")).thenReturn(booksByTitle);
		List<Book> response = resources.target("/books/get/" + book.getTitle()).request().get(new GenericType<List<Book>>() {
		});
		assertEquals(response.size(), 1);
		assertThat(response).containsAll(booksByTitle);
	}
	
	
	@Test
	public void getAllBooks() {
		when(BOOK_DAO.getAllBooks()).thenReturn(books);
		List<Book> response = resources.target("/books").request().get(new GenericType<List<Book>>() {
		});
		assertThat(response).containsAll(books);
	}
	
	@Test
	public void getLast5Books() {
		when(BOOK_DAO.getLastFiveBooks()).thenReturn(books);
		List<Book> response = resources.target("/books/lastfive").request().get(new GenericType<List<Book>>() {
		});
		assertEquals(response.size(), 5);
		assertThat(response).containsAll(books);
	}

	@Test
	public void createBook() {
		when(BOOK_DAO.getBookByISBN(book.getIsbn())).thenReturn(null);
		doNothing().when(BOOK_DAO).insertBook(book);
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.NO_CONTENT);
	}

	@Test
	public void createBookIsbnAlreadyExists() throws JsonParseException, JsonMappingException, IOException {
		when(BOOK_DAO.getBookByISBN(book.getIsbn())).thenReturn(book);

		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(),
				String.format(CustomizedErrorMessages.BOOK_ALREADY_EXISTS_IN_LIBRARY, book.getIsbn()));
	}

	@Test
	public void createBookNullBook() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(null, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_BOOK);

	}

	@Test
	public void createBookNullIsbn() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithNullIsbn, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_ISBN);

	}
	
	@Test
	public void createBookEmptyIsbn() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithoutIsbn, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_ISBN);
	}

	@Test
	public void createBookEmptyTitle() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithoutTitle, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_TITLE);
	}
	
	@Test
	public void createBookNullTitle() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithNullTitle, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_TITLE);

	}
	
	@Test
	public void createBookEmptyListOfAuthors() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithoutListOfAuthors, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_LIST_OF_AUTHORS);
	}
	
	@Test
	public void createBookNullListOfAuthors() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithNullListOfAuthors, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_LIST_OF_AUTHORS);

	}

	@Test
	public void createBookNullNumberOfPages() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithNullPages, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_NUMBER_OF_PAGES);
	}

	
	@Test
	public void createBookZeroNumberOfPages() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithZeroPages, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_NUMBER_OF_PAGES);

	}

	@Test
	public void createBookMinusNumberOfPages() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithMinusPages, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_NUMBER_OF_PAGES);
	}
	
	@Test
	public void createBookEmptyGenre() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithoutGenre, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_GENRE);
	}
	
	@Test
	public void createBookNullGenre() {
		Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bookWithNullGenre, MediaType.APPLICATION_JSON_TYPE));

		ParseResponse reply = response.readEntity(ParseResponse.class);

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
		assertEquals(reply.getCode(), 400);
		assertEquals(reply.getMessage(), CustomizedErrorMessages.INVALID_GENRE);

	}
}
