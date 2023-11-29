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

import com.example.demo.dto.Role;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired(required = true)
	RoleService roleService;
	
	@GetMapping("/all")
	public List<Role> listAllRoles(){
		return roleService.getAll();
	}
	
	@PostMapping("/add")
	public Role saveRole(@RequestBody Role role) {
		return roleService.add(role);
	}
	
	@GetMapping("/{id}")
	public Role getOneRole(@PathVariable(name="id") int id) {
		return roleService.getOne(id);
	}
	
	@PutMapping("/{id}")
	public Role updateRole(@PathVariable(name="id") int id,@RequestBody Role role) {
		
		Role prevRole = new Role();
		Role newRole = new Role();
		
		prevRole = roleService.getOne(id);
		
		prevRole.setName(role.getName());
		
		newRole = roleService.update(prevRole);
		
		return newRole;
	}
	
	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable(name="id") int id) {
		roleService.deleteOne(id);
	}
	
}
