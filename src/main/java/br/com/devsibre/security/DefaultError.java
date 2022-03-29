package br.com.devsibre.security;

import java.io.Serializable;
import java.time.Instant;

public class DefaultError implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Instant timestamp;
	private Integer status;
	private String error;
	private String path;
	
	public DefaultError() {
		
	}
	
	public DefaultError(String message, Instant timestamp, Integer status, String error, String path) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.path = path;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}
