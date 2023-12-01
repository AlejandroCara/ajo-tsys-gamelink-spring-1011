package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserDAO;
import com.example.demo.dto.User;

@Service
public class UserService implements IUserService{
	
	@Autowired(required = true)
	IUserDAO iUserDAO;
	
	@Override
	public List<User> getAll() {
		return iUserDAO.findAll();
	}

	@Override
	public User add(User user) {
		return iUserDAO.save(user);
	}

	@Override
	public User getOne(int id) {
		return iUserDAO.findById(id).get();
	}

	@Override
	public User update(User user) {
		return iUserDAO.save(user);
	}

	@Override
	public void deleteOne(int id) {
		iUserDAO.deleteById(id);
	}

	@Override
	public Page<User> getPaginatedUsers(Pageable pageable) {
		return iUserDAO.findAll(pageable);
	}

}
