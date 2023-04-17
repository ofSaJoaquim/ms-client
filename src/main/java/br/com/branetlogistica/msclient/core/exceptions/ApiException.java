package br.com.branetlogistica.msclient.core.exceptions;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final ZoneId utc = ZoneId.of("UTC");
    private final OffsetDateTime timestamp;
    private final HttpStatus status;
    private final Map<String, String> errors;

    public ApiException(HttpStatus status, String msg, Map<String, String> errors ) {
        super(msg);
        this.timestamp = OffsetDateTime.now(utc);
        this.status = status;
        this.errors = errors;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

	public Map<String, String> getErrors() {
		return errors;
	}
    
    
}