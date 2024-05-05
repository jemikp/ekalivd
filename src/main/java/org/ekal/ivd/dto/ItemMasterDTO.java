package org.ekal.ivd.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.ekal.ivd.entity.ItemMaster;
import org.ekal.ivd.entity.User;

import java.util.Optional;

@Data
@JsonSerialize
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ItemMasterDTO {
	Integer id;

	@NotNull
	String itemName;
	
	String itemType;
	
	public ItemMasterDTO(ItemMaster itemMaster) {

		Optional.ofNullable(itemMaster).ifPresent(i -> {
			this.id = i.getId();
			this.itemName = i.getItemName();
			this.itemType = i.getItemType();
		});
	}

}
