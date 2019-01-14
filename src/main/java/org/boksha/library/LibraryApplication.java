package org.boksha.library;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.boksha.library.api.BookResource;

import io.dropwizard.Application;
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
		// TODO: application initialization
	}

	@Override
	public void run(final LibraryConfiguration configuration, final Environment environment) {
		DateFormat eventDateFormat = new SimpleDateFormat(configuration.getDateFormat());
		environment.getObjectMapper().setDateFormat(eventDateFormat);
		
		BookResource bookResource = new BookResource();
		environment.jersey().register(bookResource);
	}

}
