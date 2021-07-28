package br.com.wsb.DonJose.service.exceptions;

public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileNotFoundException(String msg) {
		super(msg);
	}

	public FileNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}