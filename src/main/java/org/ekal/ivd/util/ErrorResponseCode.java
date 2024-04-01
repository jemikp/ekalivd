/**
 * 24-Mar-2024
 * @Author LALIT
 * 
 */
package org.ekal.ivd.util;

/**
 * 
 */
public enum ErrorResponseCode {

	MISSING_USER(1000,"Given User ID is not found."),
	WHATSAPP_AVAILABLE(1001,"User already exist with the given Whatsapp Number"),
	WHATSAPP_INVALID(1002,"User not available with the given Whatsapp Number"),
	WHATSAPP_NOT_VERIFIED(1003,"Whatsapp Number Not verified."),
	INVALID_OTP(1004,"Invalid OTP"),
	WHATSAPP_OTP_TIME_OVER(1005,"OTP validation time passed away."),
	IVD_ALREADY_EXIST(1006,"IVD Already Exist with the name"),
	PROGRAM_ALREADY_EXIST(1007,"Program Already Exist with the name"),
	PROGRAM_NOT_FOUND(1008,"Program not found.");

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