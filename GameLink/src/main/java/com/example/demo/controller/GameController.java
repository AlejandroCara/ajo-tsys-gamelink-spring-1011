/**
 * 
 */
package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.example.demo.dto.Game;
import com.example.demo.service.GameService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired(required = true)
	GameService gameService;

	@GetMapping("/paged")
	public ResponseEntity<List<Game>> listAllGamesPaginated(@RequestParam(defaultValue = "0") int page,
													@RequestParam(defaultValue = "10") int size) {
		Page<Game> departamentoPage = gameService.getPaginatedGames(PageRequest.of(page, size));
		List<Game> departamentoDTOs = departamentoPage.getContent().stream()
				.collect(Collectors.toList());

		return new ResponseEntity<>(departamentoDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Game> listAllGames() {
		return gameService.getAll();
	}

	@PostMapping("/add")
	public Game saveGame(@RequestBody Game game) {
		return gameService.add(game);
	}

	@GetMapping("/id/{id}")
	public Game getOneGame(@PathVariable(name = "id") int id) {
		return gameService.getOne(id);
	}

	@PutMapping("/{id}")
	public Game updateGame(@PathVariable(name = "id") int id, @RequestBody Game game) {

		Game prevGame = new Game();
		Game newGame = new Game();

		prevGame = gameService.getOne(id);

		prevGame.setTitle(game.getTitle());
		prevGame.setThumbnailUrl(game.getThumbnailUrl());
		prevGame.setUrl(game.getUrl());

		newGame = gameService.update(prevGame);

		return newGame;
	}

	@DeleteMapping("/{id}")
	public void deleteGame(@PathVariable(name = "id") int id) {
		gameService.deleteOne(id);
	}

}
