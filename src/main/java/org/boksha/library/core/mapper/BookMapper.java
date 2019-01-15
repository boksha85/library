package org.boksha.library.core.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.boksha.library.core.Book;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class BookMapper implements ResultSetMapper<Book>{

	@Override
	public Book map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		
		return new Book(r.getLong("id"), r.getString("ISBN"), r.getString("title"), r.getString("listOfAuthors"),
				r.getInt("numberOfPages"), r.getString("genre"));
	}

}
