package org.ekal.ivd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@MappedSuperclass
public class BaseEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "delflag",columnDefinition = "INT DEFAULT 0")
	int delflag = 0;

//	@Column(name = "create_timestamp")
//	LocalDateTime createTimestamp;
//
//	@Column(name = "last_update_timestamp")
//	LocalDateTime lastUpdateTimestamp;

}
