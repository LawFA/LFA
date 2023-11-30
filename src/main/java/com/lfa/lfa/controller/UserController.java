package com.lfa.lfa.controller;

import com.lfa.lfa.Request.UserLoginRequest;
import com.lfa.lfa.Request.UserRegisterRequest;
import com.lfa.lfa.domain.User;
import com.lfa.lfa.repository.UserRepository;
import com.lfa.lfa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();

        System.out.println("email: "+ email+" pw: "+password);

        User authenticatedUser = userService.authenticateUser(email, password);


        if (authenticatedUser != null) {
            // Return user information or a token in the response
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // 중복 사용자 확인 로직
        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("이미 존재하는 사용자입니다.", HttpStatus.BAD_REQUEST);
        }

        // 신규 사용자 등록
        userRepository.save(user);

        return new ResponseEntity<>("회원가입이 완료되었습니다.", HttpStatus.CREATED);
    }


    @GetMapping("/home")
    public ResponseEntity<?> home(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        // Return user information
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.removeAttribute("user");
        return ResponseEntity.ok("Logged out successfully");
    }
}