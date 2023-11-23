package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.User;

public interface IUserService {

	// Metodos del CRUD
	public List<User> getAll();

	public User add(User user);

	public User getOne(int id);

	public User update(User user);

	public void deleteOne(int id);
	
}
