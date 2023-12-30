package com.example.authorizerestfulapi.repository;

import com.example.authorizerestfulapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Optional<Users> findUsersByUsername(String username);
    Optional<Users> findUsersByEmail(String email);
    Optional<Users> findUsersByUsernameAndEmail(String username, String email);
}
