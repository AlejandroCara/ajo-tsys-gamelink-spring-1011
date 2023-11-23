package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IRoleDAO;
import com.example.demo.dto.Role;

@Service
public class RoleService implements IRoleService{
	
	@Autowired(required = true)
	IRoleDAO iRoleDAO;

	@Override
	public List<Role> getAll() {
		return iRoleDAO.findAll();
	}

	@Override
	public Role add(Role role) {
		return iRoleDAO.save(role);
	}

	@Override
	public Role getOne(int id) {
		return iRoleDAO.findById(id).get();
	}

	@Override
	public Role update(Role role) {
		return iRoleDAO.save(role);
	}

	@Override
	public void deleteOne(int id) {
		iRoleDAO.deleteById(id);
	}
	
	
}
