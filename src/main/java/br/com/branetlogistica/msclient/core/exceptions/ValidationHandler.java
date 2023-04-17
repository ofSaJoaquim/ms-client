package br.com.branetlogistica.msclient.core.exceptions;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

	private static final ZoneId utc = ZoneId.of("UTC");

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		ErrorMessage error= ErrorMessage.builder()
				.errors(errors)
				.error("VALIDATION")
				.message("Validation error")
				.timestamp(OffsetDateTime.now(utc))
				.path(request.getContextPath())
				.status(HttpStatus.BAD_REQUEST.value())
				.build();
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
}
