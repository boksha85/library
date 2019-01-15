package org.boksha.library.db;

import java.util.List;

import org.boksha.library.core.Book;
import org.boksha.library.core.mapper.BookMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;;

@RegisterMapper(BookMapper.class)
public interface BookDAO {

	@SqlQuery("select * from books")
	List<Book> getAllBooks();
	
	@SqlUpdate("insert into books (isbn, title, listofauthors, numberofpages, genre) "
			+ "values (:isbn, :title, :listOfAuthors, :numberOfPages, :genre)")
	  void insertBook(@Bind("isbn") String isbn, @Bind("title") String title, @Bind("listOfAuthors") String listOfAuthors,
			  @Bind("numberOfPages") int numberOfPages, @Bind("genre") String genre);

}
