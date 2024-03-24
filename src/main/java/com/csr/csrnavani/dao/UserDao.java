package com.csr.csrnavani.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.csr.csrnavani.dto.ErrorResponseDTO;
import com.csr.csrnavani.dto.PaginationDTO;
import com.csr.csrnavani.dto.UserDTO;
import com.csr.csrnavani.entity.User;
import com.csr.csrnavani.exception.AppException;
import com.csr.csrnavani.repository.UserRepository;
import com.csr.csrnavani.util.ErrorResponseCode;

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
		userDTO.setId(user.getId());
	}
	
	public void updateUser(int userId,@Valid UserDTO userDTO) {
		List<User> listUser = userRepository.findByIdOrWhatsApp(userId,userDTO.getWhatsApp());
		if(CollectionUtils.isEmpty(listUser)) {
			throw new AppException(new ErrorResponseDTO(ErrorResponseCode.MISSING_USER), HttpStatus.BAD_REQUEST,userId+"");
		}else if(listUser.size() > 1) {
			throw new AppException(new ErrorResponseDTO(ErrorResponseCode.WHATSAPP_AVAILABLE), HttpStatus.BAD_REQUEST,userDTO.getWhatsApp());
		}
		User user = listUser.get(0);
		updateUser(user, userDTO);
	}
	
	public void updateUser(User existingData,UserDTO userDTO) {
		existingData.setFirstName(userDTO.getFirstName());
		existingData.setLastName(userDTO.getLastName());
		existingData.setMobile(userDTO.getMobile());
		existingData.setWhatsApp(userDTO.getWhatsApp());
		
		userRepository.save(existingData);
	}
	
	public UserDTO getById(int userId) {
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isEmpty()) {
			throw new AppException(new ErrorResponseDTO(ErrorResponseCode.MISSING_USER), HttpStatus.BAD_REQUEST,userId+"");
		}
		User user = optUser.get();
		UserDTO userDTO = new UserDTO(user);
		return userDTO;
	}
	
	public PaginationDTO<UserDTO> getAllUsers(int page,int size){
		
		PaginationDTO<UserDTO> userPage = null;
		Pageable paging = PageRequest.of(page, size);
		Page<User> allUsers = userRepository.findAll(paging);
		
		if (allUsers.hasContent()) {

			Page<UserDTO> usersDTO = allUsers.map(u -> new UserDTO(u));

			userPage = new PaginationDTO<UserDTO>(usersDTO);
		}
		return userPage;
	}
}

