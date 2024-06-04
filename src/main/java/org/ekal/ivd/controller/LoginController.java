package org.ekal.ivd.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.ekal.ivd.dao.UserDao;
import org.ekal.ivd.dto.UserDTO;
import org.ekal.ivd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("LoginController")
public class LoginController {
    @Autowired
    UserDao userDao;

    @PostMapping("/sendOTP")
    public ResponseEntity<?> sendOTP(@RequestParam(name = "whatsApp") String whatsApp) {
        userDao.sendOtp(whatsApp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/validateOTP")
    public ResponseEntity<?> login(@RequestParam(name = "whatsApp") String whatsApp, @RequestParam(name = "otp") String otp) {
        var user = userDao.validateOTP(whatsApp, otp);
        if (user.getId() > 0) {
            SecurityContext context =  SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getOtp());
            context.setAuthentication(authentication);
            return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(UserDTO userDTO, HttpServletResponse httpServletResponse) throws IOException {
        User user = userDao.validateEmailAndPassword(userDTO.getEmail(),userDTO.getPassword());
        System.out.println(user);
     //   httpServletResponse.sendRedirect("http://localhost:8088/ekalivd/new_admin.html");
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
