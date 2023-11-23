package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired(required = true)
	UserService userService;
	
	@GetMapping("/all")
	public List<User> listAllUsers(){
		return userService.getAll();
	}
	
	@PostMapping("/add")
	public User saveUser(@RequestBody User user) {
		return userService.add(user);
	}
	
	@GetMapping("/{id}")
	public User getOneUser(@PathVariable(name="id") int id) {
		return userService.getOne(id);
	}
	
	@PutMapping("/{id}")
	public User updateRole(@PathVariable(name="id") int id,@RequestBody User user) {
		
		User preUser = new User();
		User newUser = new User();
		
		preUser = userService.getOne(id);
		
		preUser.setUserName(user.getUserName());
		preUser.setEmail(user.getEmail());
		preUser.setPassword(user.getPassword());
		preUser.setRole(user.getRole());
		
		newUser = userService.update(preUser);
		
		return newUser;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(name="id") int id) {
		userService.deleteOne(id);
	}
}
