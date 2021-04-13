package com.nt.niru.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.niru.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
Optional<User> findByusername(String username);
}
