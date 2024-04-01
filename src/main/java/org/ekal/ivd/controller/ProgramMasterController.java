package org.ekal.ivd.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dao.ProgramMasterDao;
import org.ekal.ivd.dto.ProgramMasterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/program")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgramMasterController {
	@Autowired
	ProgramMasterDao programMasterDao;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createProgram(@Valid @RequestBody ProgramMasterDTO programMasterDTO) {
		programMasterDao.createProgram(programMasterDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(programMasterDTO);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProgram() {

		List<ProgramMasterDTO> programDTO = programMasterDao.getProgram();
		return ResponseEntity.status(HttpStatus.OK).body(programDTO);
	}
}
