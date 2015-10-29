package com.mastek.dstl.dvo;

public class Response {

	private String Message;
	
	private Status Status;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		Message = message;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return Status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		Status = status;
	}
}
