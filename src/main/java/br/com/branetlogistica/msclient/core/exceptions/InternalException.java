package br.com.branetlogistica.msclient.core.exceptions;

import org.springframework.http.HttpStatus;

public class InternalException extends ApiException {

	private static final long serialVersionUID = 1L;

	public InternalException(String msg) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, msg, null);
	}
}