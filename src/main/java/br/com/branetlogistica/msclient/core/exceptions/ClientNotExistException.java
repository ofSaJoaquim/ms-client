package br.com.branetlogistica.msclient.core.exceptions;

import org.springframework.http.HttpStatus;

public class ClientNotExistException extends ApiException {

    private static final long serialVersionUID = 1L;

    public ClientNotExistException(String msg) {
        super(HttpStatus.BAD_REQUEST, msg,null);
    }
}