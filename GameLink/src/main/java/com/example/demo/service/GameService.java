/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IGameDAO;
import com.example.demo.dto.Game;

@Service
public class GameService implements IGameService {

	@Autowired(required = true)
	IGameDAO iGameDAO;

	@Override
	public List<Game> getAll() {
		return iGameDAO.findAll();
	}

	@Override
	public Game add(Game game) {
		return iGameDAO.save(game);
	}

	@Override
	public Game getOne(int id) {
		return iGameDAO.findById(id).get();
	}

	@Override
	public Game update(Game game) {
		return iGameDAO.save(game);
	}

	@Override
	public void deleteOne(int id) {
		iGameDAO.deleteById(id);
	}

}
