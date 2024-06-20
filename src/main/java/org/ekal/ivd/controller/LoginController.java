package org.ekal.ivd.controller;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.ekal.ivd.dao.UserDao;
import org.ekal.ivd.dto.UserDTO;
import org.ekal.ivd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("LoginController")
public class LoginController {
    @Autowired
    UserDao userDao;

    @PostMapping("/sendOTP")
    public ResponseEntity<?> sendOTP(@RequestParam(name = "whatsApp") String whatsApp, HttpSession session) {
        userDao.sendOtp(whatsApp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/validateOTP")
    public ResponseEntity<?> login(@RequestParam(name = "whatsApp") String whatsApp, @RequestParam(name = "otp") String otp, HttpSession session) {
        var user = userDao.validateOTP(whatsApp, otp);
        if (user.getId() > 0) {
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
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

    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        try {
            session.removeAttribute("user");
            session.removeAttribute("userId");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        session.invalidate();
        response.sendRedirect(session.getServletContext().getContextPath() + "/public/login.html");
    }
}
