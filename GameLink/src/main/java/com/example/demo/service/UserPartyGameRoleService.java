package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserPartyGameRoleDAO;
import com.example.demo.dto.UserPartyGameRole;

@Service
public class UserPartyGameRoleService implements IUserPartyGameRoleService{
	
	@Autowired(required = true)
	IUserPartyGameRoleDAO iUserPartyGameRoleServiceDAO;

	@Override
	public List<UserPartyGameRole> getAll() {
		return iUserPartyGameRoleServiceDAO.findAll();
	}

	@Override
	public UserPartyGameRole add(UserPartyGameRole userPartyGameRole) {
		return iUserPartyGameRoleServiceDAO.save(userPartyGameRole);
	}

	@Override
	public UserPartyGameRole getOne(int id) {
		return iUserPartyGameRoleServiceDAO.findById(id).get();
	}

	@Override
	public UserPartyGameRole update(UserPartyGameRole userPartyGameRole) {
		return iUserPartyGameRoleServiceDAO.save(userPartyGameRole);
	}

	@Override
	public void deleteOne(int id) {
		iUserPartyGameRoleServiceDAO.deleteById(id);
	}
}
