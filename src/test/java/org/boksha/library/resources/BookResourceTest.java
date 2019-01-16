package org.boksha.library.resources;

import org.boksha.library.core.Book;
import org.boksha.library.core.MockBook;
import org.boksha.library.db.BookDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.dropwizard.testing.junit.ResourceTestRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Unit tests for @link BookResource}.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookResourceTest {
	private static final BookDAO BOOK_DAO = mock(BookDAO.class);
	private Book book;
	private Book bookWithoutGenre;

	@Captor
	private ArgumentCaptor<Book> bookCaptor;

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new BookResource(BOOK_DAO))
			.build();

	@Before
	public void setUp() {
		book = MockBook.getBook();
		bookWithoutGenre = MockBook.getBookWithEmptyGenre();
	}

	@After
	public void tearDown() {
		reset(BOOK_DAO);
	}

	@Test
	public void createBook() {
		when(BOOK_DAO.getBookByISBN(any(String.class))).thenReturn(null);
		doNothing().when(BOOK_DAO).insertBook(book);
		/*final Response response = resources.target("/books/add").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);*/

	}

}
