package org.ekal.ivd.dao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.ekal.ivd.dto.ItemMasterDTO;
import org.ekal.ivd.dto.TasksDTO;
import org.ekal.ivd.entity.ItemMaster;
import org.ekal.ivd.entity.TaskItem;
import org.ekal.ivd.entity.Tasks;
import org.ekal.ivd.repository.TaskItemRepository;
import org.ekal.ivd.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class TasksDao {
    private final TasksRepository tasksRepository;
    private final TaskItemRepository taskItemRepository;

    public TasksDao(TasksRepository tasksRepository, TaskItemRepository taskItemRepository) {
        this.tasksRepository = tasksRepository;
        this.taskItemRepository = taskItemRepository;
    }

    @Transactional
    public Tasks saveTask(TasksDTO tasksDTO){
        Tasks tasks = new Tasks();
        tasks.setTaskName(tasksDTO.getTaskName());
        tasks.setTaskDescription(tasksDTO.getTaskDescription());
        Tasks savedTask = tasksRepository.save(tasks);
        log.info("Saved task with id {}",savedTask.getId());
        Set<ItemMasterDTO> items = tasksDTO.getItems();
        List<TaskItem> taskItems = items.stream()
                .map(itemDto ->{
                    TaskItem taskItem = new TaskItem();
                    ItemMaster item = new ItemMaster(itemDto);
                    item.setId(itemDto.getId());
                    taskItem.setItem(item);
                    taskItem.setTask(savedTask);
                    return taskItem;
                })
                .toList();
        log.info("Saving task items");
        List<TaskItem> savedTaskItems = taskItemRepository.saveAll(taskItems);
        log.info("Saved {} task items", savedTaskItems.size());
        return savedTask;
    }
}
