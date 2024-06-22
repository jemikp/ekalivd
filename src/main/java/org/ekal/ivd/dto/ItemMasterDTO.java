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

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

	String itemDesc;

	Set<ItemOptionDTO> itemOptions;

	public ItemMasterDTO(ItemMaster itemMaster) {

		Optional.ofNullable(itemMaster).ifPresent(i -> {
			this.id = i.getId();
			this.itemName = i.getItemName();
			this.itemType = i.getItemType();
			this.itemDesc = i.getItemDesc();
			this.itemOptions = i.getItemOptions()
					.stream()
					.map(itemOption -> {
						ItemOptionDTO itemOptionDTO = new ItemOptionDTO();
						itemOptionDTO.setId(itemOption.getId());
						itemOptionDTO.setOptionName(itemOption.getOptionName());
						return itemOptionDTO;
					}).collect(Collectors.toSet());
		});
	}

}
