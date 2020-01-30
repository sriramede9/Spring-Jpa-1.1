package com.sri.Jpanew11.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchPersonException extends RuntimeException {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	public NoSuchPersonException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
