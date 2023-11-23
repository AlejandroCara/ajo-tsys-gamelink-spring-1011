package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Role;


public interface IRoleService {

	// Metodos del CRUD
	public List<Role> getAll();

	public Role add(Role role);

	public Role getOne(int id);

	public Role update(Role role);

	public void deleteOne(int id);
	
}
