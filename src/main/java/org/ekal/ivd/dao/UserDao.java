package org.ekal.ivd.dao;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.ErrorResponseDTO;
import org.ekal.ivd.dto.PaginationDTO;
import org.ekal.ivd.dto.UserDTO;
import org.ekal.ivd.entity.User;
import org.ekal.ivd.exception.DynamicException;
import org.ekal.ivd.repository.UserRepository;
import org.ekal.ivd.util.CommonConstant;
import org.ekal.ivd.util.ErrorResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDao {
    private final static Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Autowired
    UserRepository userRepository;

    public void createUser(@Valid UserDTO userDTO) {
        Optional<User> optUser = userRepository.getByMobile(userDTO.getWhatsApp());
        if(optUser.isPresent()){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.WHATSAPP_AVAILABLE), HttpStatus.BAD_REQUEST, userDTO.getWhatsApp());
        }
        User user = new User(userDTO);
        userRepository.save(user);
        userDTO.setId(user.getId());
    }

    public void updateUser(int userId, @Valid UserDTO userDTO) {
        List<User> listUser = userRepository.findByIdOrMobile(userId, userDTO.getWhatsApp());
        if (CollectionUtils.isEmpty(listUser)) {
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.MISSING_USER), HttpStatus.BAD_REQUEST, userId + "");
        } else if (listUser.size() > 1) {
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.WHATSAPP_AVAILABLE), HttpStatus.BAD_REQUEST, userDTO.getWhatsApp());
        }
        User user = listUser.get(0);
        updateUser(user, userDTO);
    }

    public void updateUser(User existingData, UserDTO userDTO) {
        existingData.setFirstName(userDTO.getFirstName());
        existingData.setLastName(userDTO.getLastName());
        existingData.setMobile(userDTO.getMobile());

        userRepository.save(existingData);
    }

    public UserDTO getById(int userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.MISSING_USER), HttpStatus.BAD_REQUEST, userId + "");
        }
        User user = optUser.get();
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }

    public PaginationDTO<UserDTO> getAllUsers(int page, int size) {

        PaginationDTO<UserDTO> userPage = null;
        Pageable paging = PageRequest.of(page, size);
        Page<User> allUsers = userRepository.findAll(paging);

        if (allUsers.hasContent()) {

            Page<UserDTO> usersDTO = allUsers.map(u -> new UserDTO(u));

            userPage = new PaginationDTO<UserDTO>(usersDTO);
        }
        return userPage;
    }

    public Integer generateOtp() {
        SecureRandom secureRandom = new SecureRandom();
        int length = 6;
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length) - 1;
        return secureRandom.nextInt(max - min + 1) + min;
    }

    public void sendOtp(String whatsAppNo){
        User user = validateWhatsApp(whatsAppNo);
        String otp = generateOtp().toString();
        logger.info("OTP ->{}",otp);
        user.setOtp(otp);
        user.setOtp_time(LocalDateTime.now());
        userRepository.save(user);

        // Call method to send OTP in WhatsAPP
    }

    public User validateWhatsApp(String whatsAppNo){
        Optional<User> optUser = userRepository.getByMobile(whatsAppNo);
        if(optUser.isEmpty()){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.WHATSAPP_INVALID), HttpStatus.BAD_REQUEST, whatsAppNo);
        }
        User user = optUser.get();
        return user;
    }

    public User validateOTP(String whatsAppNo,String otp){
        User user = validateWhatsApp(whatsAppNo);
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime otpTime = user.getOtp_time();
        long minutes = otpTime.until(current, ChronoUnit.MINUTES);
        if(!otp.equals(user.getOtp())){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.INVALID_OTP), HttpStatus.BAD_REQUEST, otp);
        }
        if(minutes > CommonConstant.OTP_VALIDATION_TIME){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.WHATSAPP_OTP_TIME_OVER), HttpStatus.BAD_REQUEST, otp);
        }
        return user;
    }

    public User validateEmailAndPassword(String email,String password){
        Optional<User> optUser = userRepository.getByEmailAndPassword(email,password);
        if(optUser.isEmpty()){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.INVALID_EMAIL_OR_PASSWORD), HttpStatus.BAD_REQUEST, email +" - "+password);
        }
        User user = optUser.get();
        return user;
    }
}