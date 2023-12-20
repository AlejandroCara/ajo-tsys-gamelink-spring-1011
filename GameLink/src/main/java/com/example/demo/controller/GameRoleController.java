package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GameGameRole;
import com.example.demo.dto.GameRole;
import com.example.demo.service.GameGameRoleService;
import com.example.demo.service.GameRoleService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/game_role")
public class GameRoleController {

	@Autowired(required = true)
	GameRoleService gameRoleService;

	@Autowired(required = true)
	GameGameRoleService gameGameRoleService;

	@GetMapping("/all")
	public ResponseEntity<Page<GameRole>> listAllGameRoles(
			@RequestParam(name = "idGame", required = false) Integer idGame, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<GameRole> departamentoPage = gameRoleService.getPaginatedGameRole(PageRequest.of(page, size));

		if (idGame != null) {
			departamentoPage = gameRoleService.findGameRoleByGameId(PageRequest.of(page, size), idGame);
		}

		return new ResponseEntity<>(departamentoPage, HttpStatus.OK);
	}

	@PostMapping("/add")
	public GameRole saveGameRole(@RequestBody GameRole gameRole) {
		GameRole newGameRole = gameRoleService.add(gameRole);
		for (int i = 0; i < gameRole.getGameGameRole().size(); i++) {
			GameGameRole gameGameRole = new GameGameRole(gameRole.getGameGameRole().get(i).getIdGame(), newGameRole, 1);
			gameGameRoleService.add(gameGameRole);
		}
		return newGameRole;
	}

	@GetMapping("/id/{id}")
	public GameRole getOneGameRole(@PathVariable(name = "id") int id) {
		return gameRoleService.getOne(id);
	}

	@PutMapping("/{id}")
	public GameRole updateGameRole(@PathVariable(name = "id") int id, @RequestBody GameRole gameRole) {

		GameRole preGameRole = new GameRole();
		GameRole newGameRole = new GameRole();
		GameGameRole newgameGameRole = new GameGameRole();
		preGameRole = gameRoleService.getOne(id);

		preGameRole.setIcon_url(gameRole.getIcon_url());
		preGameRole.setName(gameRole.getName());
		preGameRole.setDescription(gameRole.getDescription());

		for (int i = 0; i < gameRole.getGameGameRole().size(); i++) {

			newgameGameRole.setIdGame(gameRole.getGameGameRole().get(i).getIdGame());
			newgameGameRole.setIdGameRole(newGameRole);
			gameGameRoleService.add(newgameGameRole);

		}

		newGameRole = gameRoleService.update(preGameRole);

		return newGameRole;
	}

	@DeleteMapping("/{id}")
	public void deleteGameRole(@PathVariable(name = "id") int id) {
		gameRoleService.deleteOne(id);
	}
}
