package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.User;

public interface IUserService {

	// Metodos del CRUD
	public List<User> getAll();

	public User add(User user);

	public User getOne(String userName);
	
	public User getOneByEmail(String email);

	public User getOneById(int id);

	public User update(User user);

	public void deleteOne(int id);
	
	Page<User> getPaginatedUsers(Pageable pageable);

}
