package br.com.branetlogistica.msclient.core.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<ErrorMessage> handleApiException(ApiException ex) {
        HttpStatus status = ex.getStatus();
        ErrorMessage errorDetails =
                ErrorMessage.builder()
                        .timestamp(ex.getTimestamp())
                        .status(status.value())
                        .error(status.getReasonPhrase())
                        .message(ex.getMessage())
                        .errors(ex.getErrors())
                        .build();
        return new ResponseEntity<>(errorDetails, status);
    }

}