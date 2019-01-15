package org.boksha.library;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;



public class LibraryConfiguration extends Configuration {
    
	@NotEmpty
	private String dateFormat;
	
	public String getDateFormat() {
		return dateFormat;
	}
	
	@Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
	
}
