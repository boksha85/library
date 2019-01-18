package org.boksha.library.core;

import javax.annotation.Resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Resource
@JsonIgnoreProperties
public class ParseResponse {
	
	public int code;
	public String message;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
