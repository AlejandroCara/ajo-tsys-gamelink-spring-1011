/**
 * 
 */
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

import com.example.demo.dto.Game;
import com.example.demo.service.GameService;

/**
 * 
 */
@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired(required = true)
	GameService gameService;

	@GetMapping("/all")
	public List<Game> listAllGames() {
		return gameService.getAll();
	}

	@PostMapping("/add")
	public Game saveGame(@RequestBody Game game) {
		return gameService.add(game);
	}

	@GetMapping("/{id}")
	public Game getOneGame(@PathVariable(name = "id") int id) {
		return gameService.getOne(id);
	}

	@PutMapping("/{id}")
	public Game updateGame(@PathVariable(name = "id") int id, @RequestBody Game game) {

		Game prevGame = new Game();
		Game newGame = new Game();

		prevGame = gameService.getOne(id);

		prevGame.setTitle(prevGame.getTitle());
		prevGame.setThumbnailUrl(prevGame.getThumbnailUrl());
		prevGame.setUrl(prevGame.getUrl());

		newGame = gameService.update(prevGame);

		return newGame;
	}

	@DeleteMapping("/{id}")
	public void deleteGame(@PathVariable(name = "id") int id) {
		gameService.deleteOne(id);
	}

}
