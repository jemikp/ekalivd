package org.ekal.ivd.repository;

import org.ekal.ivd.entity.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskItemRepository extends JpaRepository<TaskItem, Integer> {
}
