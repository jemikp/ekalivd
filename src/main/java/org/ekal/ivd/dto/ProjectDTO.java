package org.ekal.ivd.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.entity.Project;

import java.time.LocalDate;
import java.util.Optional;

@Data
@JsonSerialize
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProjectDTO {
	Integer id;

	String projectName;

	String projectDescription;

	Integer projectStatus;

	LocalDate startDate;

	LocalDate endDate;

	Integer ivdId;

	IVDMasterDTO ivd;

	Integer coordinatorUserId;

	UserDTO coordinatorUser;

	Integer subCoordinatorUserId;

	UserDTO subCoordinatorUser;

	Integer createdByUserId;

	UserDTO createdByUser;

	Integer modifiedByUserId;

	UserDTO modifiedByUser;
	public ProjectDTO(Project project) {

		Optional.ofNullable(project).ifPresent(p -> {
			this.id = p.getId();
			this.projectName = p.getProjectName();
			this.projectDescription = p.getProjectDescription();
			this.projectStatus = p.getProjectStatus();
			this.startDate = p.getStartDate();
			this.endDate = p.getEndDate();
			this.ivd = new IVDMasterDTO(p.getIvd());
			this.coordinatorUser = new UserDTO(p.getCoordinatorUser());
			this.subCoordinatorUser = new UserDTO(p.getSubCoordinatorUser());
		});
	}

}
