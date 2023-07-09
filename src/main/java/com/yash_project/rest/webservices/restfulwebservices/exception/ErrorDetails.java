package com.yash_project.rest.webservices.restfulwebservices.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails {
	private LocalDateTime date;
	private String msg;
	private String details;
	public ErrorDetails(LocalDateTime date, String msg, String details) {
		super();
		this.date = date;
		this.msg = msg;
		this.details = details;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public String getMsg() {
		return msg;
	}
	public String getDetails() {
		return details;
	}
	

}
