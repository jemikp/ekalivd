package org.ekal.ivd.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dao.ProjectDao;
import org.ekal.ivd.dto.PaginationDTO;
import org.ekal.ivd.dto.ProjectDTO;
import org.ekal.ivd.util.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectController {
	@Autowired
	ProjectDao projectDao;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
		projectDao.createProject(projectDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(projectDTO);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllProject(@RequestParam(name = "page", defaultValue = CommonConstant.PAGE) int page,
										 @RequestParam(name = "size", defaultValue = CommonConstant.SIZE) int size){

		PaginationDTO<ProjectDTO> projectDTOPaginationDTO = projectDao.getAllProjects(page,size);
		return ResponseEntity.status(HttpStatus.OK).body(projectDTOPaginationDTO);
	}
}
