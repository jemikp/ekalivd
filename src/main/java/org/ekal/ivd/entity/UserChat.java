package org.ekal.ivd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "user_chat")
public class UserChat extends BaseEntity{
	@Column(name = "from_id")
	Integer fromId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_id", referencedColumnName = "id", insertable = false, updatable = false)
	User user;

	@Column(name = "project_id")
	Integer projectId;

	@Column(name = "program_id")
	Integer programId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "program_id", referencedColumnName = "id", insertable = false, updatable = false)
	ProgramMaster programMaster;

	@Column(name = "task_id")
	Integer taskId;

	@Column(name = "chat_content",columnDefinition = "TEXT")
	String chatContent;

	@Column(name = "chat_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" )
	LocalDateTime chatTime;

	public UserChat(UserChatDTO userChatDTO) {

		Optional.ofNullable(userChatDTO).ifPresent(u -> {
			this.fromId = u.getFromId();
			this.chatContent = u.getChatContent();
			this.chatTime = LocalDateTime.now();
			this.projectId = u.getProjectId();
			this.programId = u.getProgramId();
			this.taskId = u.getTaskId();
		});
	}

}
