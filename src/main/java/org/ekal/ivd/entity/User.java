package org.ekal.ivd.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.ekal.ivd.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	RoleMaster roleId;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;
	
	@Column(name = "mobile",columnDefinition = "varchar(20)")
	String mobile;
	
	@Column(name = "whatsapp",columnDefinition = "varchar(20)")
	String whatsApp;

	@Column(name = "whatsapp_verified",columnDefinition = "varchar(1) default 'N'")
	String whatsAppVerified;

	@Column(name = "otp",columnDefinition = "varchar(6)")
	String otp;

	@Column(name = "otp_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" )
	LocalDateTime otp_time;

	public User(UserDTO userDTO) {

		Optional.ofNullable(userDTO).ifPresent(u -> {
			this.firstName = u.getFirstName();
			this.lastName = u.getLastName();
			this.mobile = u.getMobile();
			this.whatsApp = u.getWhatsApp();
		});
	}

}
