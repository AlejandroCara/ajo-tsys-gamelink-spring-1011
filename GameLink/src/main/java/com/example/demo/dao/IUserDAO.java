package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.User;

public interface IUserDAO extends JpaRepository<User, Integer>{

	Optional<User> findByUserName(String userName);

	Optional<User> findByEmail(String email);
}
