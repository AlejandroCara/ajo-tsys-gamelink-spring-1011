package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserPartyGameRole;

public interface IUserPartyGameRoleService {

	// Metodos del CRUD
	public List<UserPartyGameRole> getAll();

	public UserPartyGameRole add(UserPartyGameRole userPartyGameRole);

	public UserPartyGameRole getOne(int id);

	public UserPartyGameRole update(UserPartyGameRole userPartyGameRole);

	public void deleteOne(int id);
	
	public List<UserPartyGameRole> findByPartyId(int idParty);

}
