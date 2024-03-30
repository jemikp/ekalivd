/**
 * 24-Mar-2024
 * @Author LALIT
 * 
 */
package org.ekal.ivd.exception;

import org.ekal.ivd.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;

/**
 * 
 */
public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ErrorResponseDTO error;

	private HttpStatus status;

	private String description;

	public AppException(String msg) {

		super(msg);
	}

	public AppException(ErrorResponseDTO error) {

		super(error.getMessage());
		this.error = error;
	}

	public AppException(ErrorResponseDTO error, HttpStatus status) {

		super(error.getMessage());
		this.error = error;
		this.status = status;
	}

	public AppException(ErrorResponseDTO error, HttpStatus status, String description) {

		super(error.getMessage());
		this.error = error;
		this.status = status;
		this.description = description;
	}

	public AppException(String message, HttpStatus status) {

		super(message);
		this.status = status;
	}

	public ErrorResponseDTO getError() {
		return error;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}
}
