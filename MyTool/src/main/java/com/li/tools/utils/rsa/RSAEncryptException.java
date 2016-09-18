package com.li.tools.utils.rsa;

public class RSAEncryptException extends Exception{
	private static final long serialVersionUID = 1L;

	public RSAEncryptException() {
		super();
	}

	public RSAEncryptException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RSAEncryptException(String message, Throwable cause) {
		super(message, cause);
	}

	public RSAEncryptException(String message) {
		super(message);
	}

	public RSAEncryptException(Throwable cause) {
		super(cause);
	}

}
