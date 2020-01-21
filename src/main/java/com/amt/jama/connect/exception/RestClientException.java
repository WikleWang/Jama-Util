package com.amt.jama.connect.exception;

/**
 * @author wikle.wang
 */
public class RestClientException extends Exception {

    public RestClientException() {}

    public RestClientException(String message) {
        super(message);
    }

    public RestClientException(Exception e) {
        super(e.getMessage());
        setStackTrace(e.getStackTrace());
    }
}
