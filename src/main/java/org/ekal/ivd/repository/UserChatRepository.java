package org.ekal.ivd.repository;

import org.ekal.ivd.entity.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Integer> {
    List<UserChat> getByProjectId(int projectId);

    List<UserChat> getByProjectIdAndProgramId(int projectId,int programId);

    List<UserChat> getByTaskId(int taskId);
}
