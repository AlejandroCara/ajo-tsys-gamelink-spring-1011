package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.GameRole;

public interface IGameRoleDAO extends JpaRepository<GameRole, Integer>{

}
