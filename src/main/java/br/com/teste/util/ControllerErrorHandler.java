package br.com.teste.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Conflito interno em aplicação", ex.getLocalizedMessage());
		
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	
	/**
	 * 
	 * Comportamento padrão para criar erros de validação em campos.
	 * 
	 * @param ex
	 * @param result
	 * @param headers
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> falhaValidacaoCampos(Exception ex, BindingResult result, HttpHeaders headers, WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (FieldError error : result.getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : result.getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Falha ao validar campos.", errors);
		
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return falhaValidacaoCampos(ex, ex.getBindingResult(), headers, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return falhaValidacaoCampos(ex, ex.getBindingResult(), headers, request);
	}


	
}
