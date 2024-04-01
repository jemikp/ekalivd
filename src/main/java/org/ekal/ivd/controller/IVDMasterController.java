package org.ekal.ivd.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dao.IVDMasterDao;
import org.ekal.ivd.dto.IVDMasterDTO;
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
@RequestMapping("/ivd")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IVDMasterController {
	@Autowired
	IVDMasterDao ivdMasterDao;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createIVD(@Valid @RequestBody IVDMasterDTO ivdMasterDTO) {
		ivdMasterDao.createIVD(ivdMasterDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ivdMasterDTO);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getIVD() {

		List<IVDMasterDTO> ivdDTO = ivdMasterDao.getIVD();
		return ResponseEntity.status(HttpStatus.OK).body(ivdDTO);
	}
}
