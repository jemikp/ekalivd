/**
 * 24-Mar-2024
 * @Author LALIT
 * 
 */
package com.csr.csrnavani.util;

/**
 * 
 */
public enum ErrorResponseCode {

	MISSING_USER(1000,"Given User ID is not found."),
	WHATSAPP_AVAILABLE(1001,"User already exist with the given Whatsapp Number");
	
	private int code;

	private String message;

	private ErrorResponseCode(int code, String message) {

		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
