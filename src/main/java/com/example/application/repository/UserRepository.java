package com.example.application.repository;

import com.example.application.data.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<Users,Long> {
    Users getByUsername(String username);
}
