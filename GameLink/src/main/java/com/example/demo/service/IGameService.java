/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.Game;

public interface IGameService {

	// Metodos del CRUD
	public List<Game> getAll();

	public Game add(Game game);

	public Game getOne(int id);

	public Game update(Game game);

	public void deleteOne(int id);

	Page<Game> getPaginatedGames(Pageable pageable);
}
