package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.UserPartyGameRole;

public interface IUserPartyGameRoleDAO extends JpaRepository<UserPartyGameRole, Integer>{
	
	List<UserPartyGameRole> findByPartyId(int idParty);
	
}
