package com.csr.csrnavani.entity;

import java.util.Optional;

import com.csr.csrnavani.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
public class User extends BaseEntity{

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;
	
	@Column(name = "mobile")
	Integer mobile;
	
	@Column(name = "whatsapp")
	Integer whatsApp;
	
	public User(UserDTO userDTO) {

		Optional.ofNullable(userDTO).ifPresent(u -> {
			this.firstName = u.getFirstName();
			this.lastName = u.getLastName();
			this.mobile = u.getMobile();
			this.whatsApp = u.getWhatsApp();
		});
	}

}
