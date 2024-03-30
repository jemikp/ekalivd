package org.ekal.ivd.dto;

import org.ekal.ivd.util.ErrorResponseCode;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@JsonSerialize
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ErrorResponseDTO {
	int code;

	String message;

	String description;

	public ErrorResponseDTO(ErrorResponseCode errorResponseCode) {

		this.code = errorResponseCode.getCode();
		this.message = errorResponseCode.getMessage();
	}

	public ErrorResponseDTO(int code, String message) {

		this.code = code;
		this.message = message;
	}

	public ErrorResponseDTO(ErrorResponseCode errorResponseCode, String description) {

		this.code = errorResponseCode.getCode();
		this.message = errorResponseCode.getMessage();
		this.description = description;
	}

	public ErrorResponseDTO(int code, String message, String description) {

		this.code = code;
		this.message = message;
		this.description = description;
	}

}
