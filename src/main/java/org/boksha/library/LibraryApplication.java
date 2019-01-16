package org.boksha.library;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.boksha.library.db.BookDAO;
import org.boksha.library.resources.BookResource;
import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class LibraryApplication extends Application<LibraryConfiguration> {

	public static void main(final String[] args) throws Exception {
		new LibraryApplication().run(args);
	}

	@Override
	public String getName() {
		return "Library";
	}

	@Override
	public void initialize(final Bootstrap<LibraryConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<LibraryConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(LibraryConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
	}

	@Override
	public void run(final LibraryConfiguration configuration, final Environment environment) {
		DateFormat eventDateFormat = new SimpleDateFormat(configuration.getDateFormat());
		environment.getObjectMapper().setDateFormat(eventDateFormat);

		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
		final BookDAO dao = jdbi.onDemand(BookDAO.class);
		environment.jersey().register(new BookResource(dao));
	}

}
