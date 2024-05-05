package org.ekal.ivd.controller;

import java.util.List;

import org.ekal.ivd.dao.ItemMasterDao;
import org.ekal.ivd.dto.ItemMasterDTO;
import org.ekal.ivd.dto.UserDTO;
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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/item")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemMasterController {
	@Autowired
	ItemMasterDao itemMasterDao;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createItem(@Valid @RequestBody ItemMasterDTO itemMasterDTO) {
		itemMasterDao.createItemMaster(itemMasterDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(itemMasterDTO);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getItem() {

		List<ItemMasterDTO> itemDTO = itemMasterDao.getItem();
		return ResponseEntity.status(HttpStatus.OK).body(itemDTO);
	}
	
//	@PutMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> updateUser(@PathVariable(name = "userId") int userId,@Valid @RequestBody UserDTO userDTO) {
//		userDao.updateUser(userId,userDTO);
//		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
//	}
	
	@GetMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getItemById(@PathVariable(name = "itemId") int itemId) {
		ItemMasterDTO itemMasterDTO = itemMasterDao.getItemById(itemId);
		return ResponseEntity.status(HttpStatus.OK).body(itemMasterDTO);
	}
	
}
