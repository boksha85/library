package org.boksha.library.db;

import java.util.List;

import org.boksha.library.core.Book;
import org.boksha.library.core.mapper.BookMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;;

@RegisterMapper(BookMapper.class)
public interface BookDAO {

	@SqlQuery("select * from books")
	List<Book> getAllBooks();

}
