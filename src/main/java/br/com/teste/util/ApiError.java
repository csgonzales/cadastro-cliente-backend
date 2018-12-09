package br.com.teste.util;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;


@Getter
@JsonInclude(Include.NON_EMPTY)
public class ApiError {
	
	private HttpStatus status;
    private String message;
    private String error;
    private List<String> errors;
 
    private ApiError() {
    	status = null;
    	message = null;
    	error = "";
    	errors = Collections.emptyList();
    }
    
    public ApiError(HttpStatus status, String message, List<String> errors) {
        this();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
    
    
 
    public ApiError(HttpStatus status, String message, String error) {
        this();
        this.status = status;
        this.message = message;
        this.error = error;
    }

}
