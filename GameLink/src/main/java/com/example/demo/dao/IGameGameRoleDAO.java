package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.GameGameRole;
import com.example.demo.dto.User;

public interface IGameGameRoleDAO extends JpaRepository<GameGameRole, Integer>{

	Optional<GameGameRole> findByIdGameRoleId(int idGameRole);
	Optional<GameGameRole> findByIdGameId(int idGame);
	
}
