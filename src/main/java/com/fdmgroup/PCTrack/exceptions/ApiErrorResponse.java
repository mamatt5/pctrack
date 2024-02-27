package com.fdmgroup.PCTrack.exceptions;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiErrorResponse {
	private String message;
	
	@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
	private List<String> causes;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<String> getCauses() {
		return causes;
	}
	
	public void setCauses(List<String> causes) {
		this.causes = causes;
	}
	
	public ApiErrorResponse() {
		
	}
	
	public ApiErrorResponse(String message, List<String> causes) {
		super();
		this.message = message;
		this.causes = causes;
	}
	
	public ApiErrorResponse(String message) {
		super();
		this.message = message;
	}
}
