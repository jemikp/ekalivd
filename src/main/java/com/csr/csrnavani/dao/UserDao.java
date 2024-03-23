package com.csr.csrnavani.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csr.csrnavani.dto.UserDTO;
import com.csr.csrnavani.entity.User;
import com.csr.csrnavani.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDao {
	private final static Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	@Autowired
	UserRepository userRepository;
	
	public void createUser(@Valid UserDTO userDTO) {
		User user = new User(userDTO);
		userRepository.save(user);
		System.out.println("==========>"+user.getId());
		userDTO.setId(user.getId());
	}
}
