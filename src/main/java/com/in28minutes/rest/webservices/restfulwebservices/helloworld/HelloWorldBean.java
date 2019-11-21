package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
	
	private String message;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	public HelloWorldBean(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}

	/**
	 * @param message the message to set
	 */
//	public void setMessage(String message) {
//		this.message = message;
//	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

}
