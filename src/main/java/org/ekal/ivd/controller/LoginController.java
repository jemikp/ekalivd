package org.ekal.ivd.controller;


import org.ekal.ivd.dao.UserDao;
import org.ekal.ivd.dto.UserDTO;
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

@RestController
@RequestMapping("LoginController")
public class LoginController {
    @Autowired
    UserDao userDao;

    @PostMapping("/sendOTP")
    public ResponseEntity<?> sendOTP(@RequestParam(name = "whatsApp") String whatsApp) {
        System.out.println(whatsApp);
        userDao.sendOtp(whatsApp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/validateOTP")
    public ResponseEntity<?> login(@RequestParam(name = "whatsApp") String whatsApp, @RequestParam(name = "otp") String otp) {
        var user = userDao.validateOTP(whatsApp, otp);
        if (user.getId() > 0) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), user.getOtp());
            context.setAuthentication(authentication);
            return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }
    }
}
