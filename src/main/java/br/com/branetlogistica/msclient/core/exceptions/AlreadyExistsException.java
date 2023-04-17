package br.com.branetlogistica.msclient.core.exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends ApiException {

    private static final long serialVersionUID = 1L;

    public AlreadyExistsException(String msg) {
        super(HttpStatus.BAD_REQUEST, msg,null);
    }
}