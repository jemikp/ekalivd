package org.ekal.ivd.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.ekal.ivd.dao.TasksDao;
import org.ekal.ivd.dto.TasksDTO;
import org.ekal.ivd.entity.Tasks;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TasksDao tasksDao;

    public TasksController(TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@Valid @RequestBody TasksDTO tasksDTO) {
        Tasks tasks = tasksDao.saveTask(tasksDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tasks);
    }

    /*@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTask(@Valid @RequestBody TasksDTO tasksDTO) {
        Tasks tasks = tasksDao.getAllTasks();
        return ResponseEntity.status(HttpStatus.CREATED).body(tasks);
    }*/
}
