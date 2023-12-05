package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User;
import com.example.demo.security.GameLinkUserDetails;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	@Autowired(required = true)
	UserService userService;
	
	// GET /api/departamentos/all?page=0&size=10
	@GetMapping("/all")
	public ResponseEntity<List<User>> listAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		
		Page<User> departamentoPage = userService.getPaginatedUsers(PageRequest.of(page, size));
		List<User> departamentoDTOs = departamentoPage.getContent().stream().map(this::convertToDTO)
				.collect(Collectors.toList());

		return new ResponseEntity<>(departamentoDTOs, HttpStatus.OK);
	}

	@PostMapping("/add")
	public User saveUser(@RequestBody User user) {
		return userService.add(user);
	}

	@GetMapping("/id/{id}")
	public User getOneUser(@PathVariable(name = "id") int id) {
		return userService.getOneById(id);
	}
	
	@GetMapping("/{userName}")
	public User getByUserName(@PathVariable(name = "userName") String userName) {
		return userService.getOne(userName);
	}
	
	@PutMapping("/test")
	public void test(Authentication authentication) {
		try {
			GameLinkUserDetails ud = (GameLinkUserDetails) authentication.getPrincipal();
			User user = userService.getOne(ud.getUsername());
			System.out.println("Logeado como: " + user.getRole().getName());
		} catch (Exception e) {
			System.out.println("No hay token");
		}
	}
	
	@PutMapping("/{id}")
	public User updateRole(@PathVariable(name = "id") int id, @RequestBody User user) {

		User preUser = new User();
		User newUser = new User();

		preUser = userService.getOneById(id);

		preUser.setUserName(user.getUserName());
		preUser.setEmail(user.getEmail());
		preUser.setPassword(user.getPassword());
		preUser.setRole(user.getRole());

		newUser = userService.update(preUser);

		return newUser;
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(name = "id") int id) {
		userService.deleteOne(id);
	}
	
	@GetMapping("/{email}")
    public User getByEmail(@PathVariable("email") String email){
        return  userService.getOneByEmail(email);
    }


	// GET /api/departamentos/paginated?page=0&size=10
	@GetMapping("/paginated")
	public ResponseEntity<List<User>> getPaginatedDepartamentos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<User> departamentoPage = userService.getPaginatedUsers(PageRequest.of(page, size));
		List<User> departamentoDTOs = departamentoPage.getContent().stream().map(this::convertToDTO)
				.collect(Collectors.toList());

		return new ResponseEntity<>(departamentoDTOs, HttpStatus.OK);
	}

	private User convertToDTO(User user) {
		return new User(user.getId(), user.getUserName(), user.getPassword(), user.getEmail(), user.getRole());
	}
}
