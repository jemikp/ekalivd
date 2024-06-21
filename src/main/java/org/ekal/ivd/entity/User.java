package org.ekal.ivd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.ekal.ivd.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.Optional;


@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
public class User extends BaseEntity{

	@Column(name = "role_id")
	Integer roleId;
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	RoleMaster role;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "email",columnDefinition = "varchar(50)")
	String email;

	@Column(name = "mobile",columnDefinition = "varchar(20)")
	String mobile;

	@Column(name = "password",columnDefinition = "varchar(500)")
	String password;
	
	@Column(name = "otp",columnDefinition = "varchar(6)")
	String otp;

	@Column(name = "otp_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" )
	LocalDateTime otp_time;
	
	@Column(name = "reporting_to")
	Integer reportingTo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reporting_to", referencedColumnName = "id", insertable = false, updatable = false)
	User userReporting;

	public User(UserDTO userDTO) {

		Optional.ofNullable(userDTO).ifPresent(u -> {
			this.firstName = u.getFirstName();
			this.lastName = u.getLastName();
			this.mobile = StringUtils.isEmpty(u.getMobile()) ? u.getWhatsApp() : u.getMobile() ;
			this.roleId = u.getRoleId();
			this.reportingTo = u.getReportingTo();
			this.delflag = 0;
			this.email = u.getEmail();
		});
		
	}

}
