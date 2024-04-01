package org.ekal.ivd.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dao.UserChatDao;
import org.ekal.ivd.dto.UserChatDTO;
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

import java.util.List;

@RestController
@RequestMapping("/userchat")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChatController {
	@Autowired
	UserChatDao userChatDao;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUserChat(@Valid @RequestBody UserChatDTO userChatDTO) {
		userChatDao.createUserChat(userChatDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(userChatDTO);
	}

	@GetMapping(value = "/chat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getChat(@RequestParam(name = "projectId",required = false) Integer projectId,
									 @RequestParam(name = "programId",required = false) Integer programId,
									 @RequestParam(name = "taskId",required = false) Integer taskId) {

		List<UserChatDTO> userChatDTO = userChatDao.getUserChat(projectId,programId,taskId);
		return ResponseEntity.status(HttpStatus.OK).body(userChatDTO);
	}
}
