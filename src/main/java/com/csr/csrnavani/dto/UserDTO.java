package com.csr.csrnavani.dto;

import java.util.Optional;

import com.csr.csrnavani.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class UserDTO {
	Integer id;

	@NotNull
	String firstName;

	String lastName;
	
	String mobile;
	
	@NotNull
	String whatsApp;
	
	String whatsAppVerified;
	
	public UserDTO(User user) {

		Optional.ofNullable(user).ifPresent(u -> {
			this.id = u.getId();
			this.firstName = u.getFirstName();
			this.lastName = u.getLastName();
			this.mobile = u.getMobile();
			this.whatsApp = u.getWhatsApp();
			this.whatsAppVerified = u.getWhatsAppVerified();
		});
	}

}
