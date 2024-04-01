package org.ekal.ivd.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.entity.User;

import java.util.Optional;

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

	RoleMasterDTO role;
	
	public UserDTO(User user) {

		Optional.ofNullable(user).ifPresent(u -> {
			this.id = u.getId();
			this.firstName = u.getFirstName();
			this.lastName = u.getLastName();
			this.mobile = u.getMobile();
			this.whatsApp = u.getWhatsApp();
			this.whatsAppVerified = u.getWhatsAppVerified();
			this.role = new RoleMasterDTO(u.getRole());
		});
	}

}