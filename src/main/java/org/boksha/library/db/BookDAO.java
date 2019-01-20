package org.boksha.library.db;

import java.util.List;

import org.boksha.library.core.Book;
import org.boksha.library.core.mapper.BookMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;;

@RegisterMapper(BookMapper.class)
public interface BookDAO {

	@SqlQuery("select * from books")
	List<Book> getAllBooks();
	
	@SqlQuery("select * from books order by id desc limit 5")
	List<Book> getLastFiveBooks();
	
	@SqlQuery("select * from books where UPPER(title) like UPPER(:condition)")
	List<Book> getInformationAboutBook(@Bind("condition") String condition);

	@SqlUpdate("insert into books (isbn, title, listofauthors, numberofpages, genre) "
			+ "values (:isbn, :title, :listOfAuthors, :numberOfPages, :genre)")
	void insertBook(@BindBean Book book);

	@SqlQuery("select * from books where isbn=:isbn")
	Book getBookByISBN(@Bind("isbn") String isbn);

}
