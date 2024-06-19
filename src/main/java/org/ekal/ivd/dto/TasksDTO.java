package org.ekal.ivd.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@JsonSerialize
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class TasksDTO {
    private String taskName;
    private String taskDescription;
    private Set<ItemMasterDTO> items;
}
