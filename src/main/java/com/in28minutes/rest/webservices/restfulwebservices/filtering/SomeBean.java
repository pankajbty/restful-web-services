package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	private String field2;
	private String field3;
	
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	/**
	 * @return the field1
	 */
	public String getField1() {
		return field1;
	}
	/**
	 * @param field1 the field1 to set
	 */
	public void setField1(String field1) {
		this.field1 = field1;
	}
	/**
	 * @return the field2
	 */
	public String getField2() {
		return field2;
	}
	/**
	 * @param field2 the field2 to set
	 */
	public void setField2(String field2) {
		this.field2 = field2;
	}
	/**
	 * @return the field3
	 */
	public String getField3() {
		return field3;
	}
	/**
	 * @param field3 the field3 to set
	 */
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	
	
}
