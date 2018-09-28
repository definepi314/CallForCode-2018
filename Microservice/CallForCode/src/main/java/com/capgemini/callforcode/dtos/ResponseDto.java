package com.capgemini.callforcode.dtos;

import java.util.List;

public class ResponseDto {

	private Object response;
	private String message;
	private String exception;

	public ResponseDto() {

	}

	public ResponseDto(List<Object> response, String message, String exception) {
		super();
		this.response = response;
		this.message = message;
		this.exception = exception;
	}

	public ResponseDto(List<Object> response, String message) {
		super();
		this.response = response;
		this.message = message;
	}

	public ResponseDto(String exception) {
		super();
		this.exception = exception;
	}

	public ResponseDto(List<Object> response) {
		super();
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
