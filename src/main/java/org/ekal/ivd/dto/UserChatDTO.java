package org.ekal.ivd.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.entity.User;
import org.ekal.ivd.entity.UserChat;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@JsonSerialize
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserChatDTO {
	Integer id;

	int fromId;

	Integer projectId;

	Integer departmentId;

	Integer taskId;

	String chatContent;

	LocalDateTime chatTime;

	User user;
	public UserChatDTO(UserChat userChat) {

		Optional.ofNullable(userChat).ifPresent(u -> {
			this.id = u.getId();
			this.projectId = u.getProjectId();
			this.departmentId = u.getDepartmentId();
			this.taskId = u.getTaskId();
			this.chatContent = u.getChatContent();
			this.chatTime = u.getChatTime();
			this.user = u.getFromId();
		});
	}

}
