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

import com.example.demo.dto.UserPartyGameRole;
import com.example.demo.service.UserPartyGameRoleService;

@RestController
@RequestMapping("/user_party_game_role")
public class UserPartyGameRoleController {
	
	@Autowired(required = true)
	UserPartyGameRoleService userPartyGameRoleService;
	
	@GetMapping("/all")
	public List<UserPartyGameRole> listAllUserPartyGameRoles() {
		return userPartyGameRoleService.getAll();
	}

	@PostMapping("/add")
	public UserPartyGameRole saveUserPartyGameRole(@RequestBody UserPartyGameRole userPartyGameRole) {
		return userPartyGameRoleService.add(userPartyGameRole);
	}

	@GetMapping("/{id}")
	public UserPartyGameRole getOneUserPartyGameRole(@PathVariable(name = "id") int id) {
		return userPartyGameRoleService.getOne(id);
	}

	@PutMapping("/{id}")
	public UserPartyGameRole updateUserPartyGameRole(@PathVariable(name = "id") int id, @RequestBody UserPartyGameRole userPartyGameRole) {

		UserPartyGameRole preUserPartyGameRole = new UserPartyGameRole();
		UserPartyGameRole newUserPartyGameRole = new UserPartyGameRole();

		preUserPartyGameRole = userPartyGameRoleService.getOne(id);

		preUserPartyGameRole.setParty(userPartyGameRole.getParty());
		preUserPartyGameRole.setUser(userPartyGameRole.getUser());
		preUserPartyGameRole.setGameRole(userPartyGameRole.getGameRole());

		newUserPartyGameRole = userPartyGameRoleService.update(preUserPartyGameRole);

		return newUserPartyGameRole;
	}

	@DeleteMapping("/{id}")
	public void deleteUserPartyGameRole(@PathVariable(name = "id") int id) {
		userPartyGameRoleService.deleteOne(id);
	}
}
