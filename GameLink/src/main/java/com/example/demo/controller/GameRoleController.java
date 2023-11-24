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

import com.example.demo.dto.Event;
import com.example.demo.dto.GameRole;
import com.example.demo.service.GameRoleService;

@RestController
@RequestMapping("/game_role")
public class GameRoleController {
	
	@Autowired(required = true)
	GameRoleService gameRoleService;
	
	@GetMapping("/all")
	public List<GameRole> listAllGameRoles() {
		return gameRoleService.getAll();
	}

	@PostMapping("/add")
	public GameRole saveGameRole(@RequestBody GameRole gameRole) {
		return gameRoleService.add(gameRole);
	}

	@GetMapping("/{id}")
	public GameRole getOneGameRole(@PathVariable(name = "id") int id) {
		return gameRoleService.getOne(id);
	}

	@PutMapping("/{id}")
	public GameRole updateGameRole(@PathVariable(name = "id") int id, @RequestBody GameRole gameRole) {

		GameRole preGameRole = new GameRole();
		GameRole newGameRole = new GameRole();

		preGameRole = gameRoleService.getOne(id);

		preGameRole.setIcon_url(gameRole.getIcon_url());
		preGameRole.setName(gameRole.getName());
		preGameRole.setDescription(gameRole.getDescription());

		newGameRole = gameRoleService.update(preGameRole);

		return newGameRole;
	}

	@DeleteMapping("/{id}")
	public void deleteGameRole(@PathVariable(name = "id") int id) {
		gameRoleService.deleteOne(id);
	}
}