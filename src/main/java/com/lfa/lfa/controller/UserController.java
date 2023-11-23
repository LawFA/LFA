package com.lfa.lfa.controller;

import com.lfa.lfa.domain.User;
import com.lfa.lfa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password, Model model, HttpSession session) {

        User authenticatedUser = userService.authenticateUser(email, password);

        if (authenticatedUser != null) {
            // 로그인 성공
            session.setAttribute("user", authenticatedUser);
            return "redirect:/user/home";
        } else {
            // 로그인 실패
            model.addAttribute("error", "Invalid email or password");
            return "login";

        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        // 세션에서 사용자 정보를 가져옵니다.
        User user = (User) session.getAttribute("user");

        // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트합니다.
        if (user == null) {
            return "redirect:/user/login";
        }

        // 사용자 정보를 모델에 추가하여 화면에 표시합니다.
        model.addAttribute("username", user.getUsername());
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 사용자 정보를 제거하고 로그인 페이지로 리다이렉트합니다.
        session.removeAttribute("user");
        return "redirect:/user/login";
    }
}