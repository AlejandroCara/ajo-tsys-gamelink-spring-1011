package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.UserPartyGameRole;

public interface IUserPartyGameRoleDAO extends JpaRepository<UserPartyGameRole, Integer>{

}
