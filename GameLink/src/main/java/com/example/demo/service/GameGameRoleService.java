package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IGameGameRoleDAO;
import com.example.demo.dto.GameGameRole;

@Service
public class GameGameRoleService implements IGameGameRoleService {

	@Autowired(required = true)
	IGameGameRoleDAO iGameGameRoleDAO;

	@Override
	public List<GameGameRole> getAll() {
		return iGameGameRoleDAO.findAll();
	}

	@Override
	public GameGameRole add(GameGameRole gameGameRole) {
		return iGameGameRoleDAO.save(gameGameRole);
	}

	@Override
	public GameGameRole getOne(int id) {
		return iGameGameRoleDAO.findById(id).get();
	}

	@Override
	public GameGameRole update(GameGameRole gameGameRole) {
		return iGameGameRoleDAO.save(gameGameRole);
	}

	@Override
	public void deleteOne(int id) {
		iGameGameRoleDAO.deleteById(id);
	}

	@Override
	public GameGameRole findByIdGameRoleId(int idGameRole) {
		return iGameGameRoleDAO.findByIdGameRoleId(idGameRole).get();
	}

	@Override
	public GameGameRole findByIdGameId(int idGame) {
		return iGameGameRoleDAO.findByIdGameId(idGame).get();
	}




}
