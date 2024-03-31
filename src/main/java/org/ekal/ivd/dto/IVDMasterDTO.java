package org.ekal.ivd.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.entity.IVDMaster;

import java.util.Optional;

@Data
@JsonSerialize
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class IVDMasterDTO {
	Integer id;

	String name;

	public IVDMasterDTO(IVDMaster ivdMaster) {

		Optional.ofNullable(ivdMaster).ifPresent(i -> {
			this.id = i.getId();
			this.name = i.getName();
		});
	}

}
