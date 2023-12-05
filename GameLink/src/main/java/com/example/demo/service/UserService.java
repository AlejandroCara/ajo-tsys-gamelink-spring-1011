package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserDAO;
import com.example.demo.dto.User;

@Service
public class UserService implements IUserService{
	
	@Autowired(required = true)
	IUserDAO iUserDAO;
	
	@Autowired(required = true)
    private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAll() {
		return iUserDAO.findAll();
	}

	@Override
	public User add(User user) {
		Optional<User> theUser = iUserDAO.findByUserName(user.getUserName());
        if (theUser.isPresent()){
            //throw new UserAlreadyExistsException("A user with " +user.getEmail() +" already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return iUserDAO.save(user);
	}
	
	@Override
	public User getOne(String userName) {
		return iUserDAO.findByUserName(userName).get();
	}
	
	@Override
	public User getOneById(int id) {
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

	@Override
	public User getOneByEmail(String email) {
		return iUserDAO.findByEmail(email).get();
	}

}
