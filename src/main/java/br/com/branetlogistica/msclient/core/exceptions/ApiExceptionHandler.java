package br.com.branetlogistica.msclient.core.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.branetlogistica.msclient.core.context.Context;
import feign.Util;



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
                        .path(Context.getContextData()!=null && !Util.isBlank(Context.getContextData().getUrl())?Context.getContextData().getUrl():null)
                        .build();
        return new ResponseEntity<>(errorDetails, status);
    }

}