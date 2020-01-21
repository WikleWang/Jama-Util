package com.amt.jama.connect.exception;

/**
 * @author WangWei
 */
public class HttpException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer code;
    private String message;


    public HttpException() {
    }
    public HttpException(Integer code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
