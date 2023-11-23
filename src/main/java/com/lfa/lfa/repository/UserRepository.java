package com.lfa.lfa.repository;

import com.lfa.lfa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Additional custom queries

    // Find users by username
    List<User> findByUsername(String username);

    // Find users by email
    Optional<User> findByEmail(String email);

    // Find users by username and email
    List<User> findByUsernameAndEmail(String username, String email);

    // Find users by username containing a given string (case-insensitive)
    List<User> findByUsernameContainingIgnoreCase(String partialUsername);

    // Example of a native query
    @Query(value = "SELECT * FROM User u WHERE u.useremail LIKE %:partialUsername%", nativeQuery = true)
    List<User> findByPartialUsernameNativeQuery(@Param("partialUsername") String partialUsername);
}
