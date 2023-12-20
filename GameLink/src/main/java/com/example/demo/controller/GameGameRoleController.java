package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GameGameRole;
import com.example.demo.service.GameGameRoleService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/game_game_role")
public class GameGameRoleController {

	@Autowired(required = true)
	GameGameRoleService gameGameRoleService;

	@GetMapping("/all")
	public List<GameGameRole> listAllGameGameRoles() {
		return gameGameRoleService.getAll();
	}

	@PostMapping("/add")
	public GameGameRole saveGameGameRole(@RequestBody GameGameRole gameGameRole) {
		return gameGameRoleService.add(gameGameRole);
	}

	@GetMapping("/{id}")
	public GameGameRole getOneGameGameRole(@PathVariable(name = "id") int id) {
		return gameGameRoleService.getOne(id);
	}
	
	@GetMapping("/idGameRole/{id}")
	public GameGameRole getOneGameGameRoleByIdGame(@PathVariable(name = "id") int id) {
		return gameGameRoleService.findByIdGameId(id);
	}

	@PutMapping("/{id}")
	public GameGameRole updateGameGameRole(@PathVariable(name = "id") int id, @RequestBody GameGameRole gameGameRole) {

		GameGameRole prevGameGameRole = new GameGameRole();
		GameGameRole newGameGameRole = new GameGameRole();

		prevGameGameRole = gameGameRoleService.getOne(id);

		prevGameGameRole.setIdGame(gameGameRole.getIdGame());
		prevGameGameRole.setIdGameRole(gameGameRole.getIdGameRole());
		prevGameGameRole.setQuantity(gameGameRole.getQuantity());

		newGameGameRole = gameGameRoleService.update(prevGameGameRole);

		return newGameGameRole;
	}

	@DeleteMapping("/{id}")
	public void deleteGameGameRole(@PathVariable(name = "id") int id) {
		gameGameRoleService.deleteOne(id);
	}

}
