package com.postTheProblem;

public class ServletResponse {
	private int status_code;
	private String status_message;
	private Object response;

	public ServletResponse() {
		// TODO Auto-generated constructor stub
	}

	public ServletResponse(int status_code, String status_message, Object response) {
		super();
		this.status_code = status_code;
		this.status_message = status_message;
		this.response = response;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getStatus_message() {
		return status_message;
	}

	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
