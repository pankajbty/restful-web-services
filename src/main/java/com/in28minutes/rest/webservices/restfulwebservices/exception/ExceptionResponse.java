package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {
	private Date timeStamp;
	private String message;
	private String details;
	public ExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	
	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	
	
}
