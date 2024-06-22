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
import org.ekal.ivd.entity.ItemOption;

import java.util.Optional;

@Data
@JsonSerialize
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ItemOptionDTO {
    Integer id;

    @NotNull
    String optionName;

    ItemMasterDTO item;


    public ItemOptionDTO(ItemOption itemOption) {

        Optional.ofNullable(itemOption).ifPresent(i -> {
            this.id = i.getId();
            this.optionName = i.getOptionName();
            this.item = new ItemMasterDTO(i.getItem());
        });
    }
}
