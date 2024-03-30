package org.ekal.ivd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.ekal.ivd.dao.UserDao;
import org.ekal.ivd.dto.PaginationDTO;
import org.ekal.ivd.dto.UserDTO;
import org.ekal.ivd.util.CommonConstant;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
	@Autowired
	UserDao userDao;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
		userDao.createUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}

	@PutMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@PathVariable(name = "userId") int userId,@Valid @RequestBody UserDTO userDTO) {
		userDao.updateUser(userId,userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}
	
	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserById(@PathVariable(name = "userId") int userId) {

		UserDTO userDTO = userDao.getById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllBin(@RequestParam(name = "page", defaultValue = CommonConstant.PAGE) int page,
			@RequestParam(name = "size", defaultValue = CommonConstant.SIZE) int size)
			{

		PaginationDTO<UserDTO> userPageDTO = userDao.getAllUsers(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(userPageDTO);
	}

	@GetMapping(value = "/{whatsApp}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllBin(@PathVariable(name = "whatsApp") String whatsApp)
	{
		userDao.sendOtp(whatsApp);
		return ResponseEntity.status(HttpStatus.OK).body("OTP Validated successfully");
	}
}
