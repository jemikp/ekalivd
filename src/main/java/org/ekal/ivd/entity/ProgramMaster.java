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
import org.ekal.ivd.dto.ProgramMasterDTO;

import java.util.Optional;


@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "program_master")
public class ProgramMaster extends BaseEntity{

	@Column(name = "name",columnDefinition = "varchar(50)",nullable = false)
	String name;

	@Column(name = "delflag",columnDefinition = "INT DEFAULT 0")
	int delflag = 0;

	public ProgramMaster(ProgramMasterDTO programMasterDTO) {

		Optional.ofNullable(programMasterDTO).ifPresent(p -> {
			this.id = p.getId();
			this.name = p.getName();
		});
	}

}
