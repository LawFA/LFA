package com.lfa.lfa.service;

import com.lfa.lfa.domain.User;
import com.lfa.lfa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User authenticateUser(String email, String password) {
        // 이메일을 기반으로 사용자를 데이터베이스에서 조회
        Optional<User> user_check = userRepository.findByEmail(email);

        User user;
        if (user_check.isPresent()) {
            user = user_check.get();
            // Now you can safely use the 'user' object.
            System.out.println(user);
            if (user != null && password.equals(user.getPassword())) {
                // 비밀번호 일치
                return user;
            } else {
                // 사용자가 없거나 비밀번호 불일치
                return null;
            }
        } else {
            return null;
        }

    }
}
