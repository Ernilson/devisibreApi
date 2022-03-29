package br.com.devsibre.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedNullException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UnsuportedNullException(String exception) {
		super(exception);
	}
	
}
