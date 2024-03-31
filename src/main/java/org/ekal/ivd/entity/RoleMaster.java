package org.ekal.ivd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.UserChatDTO;

import java.time.LocalDateTime;
import java.util.Optional;


@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "role_master")
public class RoleMaster extends BaseEntity{

	@Column(name = "role_name",columnDefinition = "VARCHAR(50)")
	String roleName;

	@Column(name = "delflag",columnDefinition = "INT DEFAULT 0")
	int delflag;

}
