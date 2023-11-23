package com.lfa.lfa.controller;

import com.lfa.lfa.Request.OrganizationLoginRequest;
import com.lfa.lfa.Request.UserLoginRequest;
import com.lfa.lfa.domain.Organization;
import com.lfa.lfa.domain.User;
import com.lfa.lfa.service.OrganizationService;
import com.lfa.lfa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationservice) {
        this.organizationService = organizationservice;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody OrganizationLoginRequest organizationLoginRequest) {
        String email = organizationLoginRequest.getEmail();
        String password = organizationLoginRequest.getPassword();

        System.out.println("email: "+ email+" pw: "+password);

        Organization authenticatedUser = organizationService.authenticateOrganization(email, password);

        if (authenticatedUser != null) {
            // Return user information or a token in the response
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        // 세션에서 사용자 정보를 가져옵니다.
        Organization organization= (Organization) session.getAttribute("organization");

        // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트합니다.
        if (organization == null) {
            return "redirect:/organization/login";
        }

        // 사용자 정보를 모델에 추가하여 화면에 표시합니다.
        model.addAttribute("organization", organization.getOrganizationName());
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 사용자 정보를 제거하고 로그인 페이지로 리다이렉트합니다.
        session.removeAttribute("organization");
        return "redirect:/organization/login";
    }
}