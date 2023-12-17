package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IGameRoleDAO;
import com.example.demo.dto.GameRole;

import jakarta.persistence.EntityManager;

@Service
public class GameRoleService implements IGameRoleService{

	@Autowired(required = true)
	IGameRoleDAO iGameRoleDAO;

	
	@Override
	public List<GameRole> getAll() {
		return iGameRoleDAO.findAll();
	}

	@Override
	public GameRole add(GameRole gameRole) {
		return iGameRoleDAO.save(gameRole);
	}

	@Override
	public GameRole getOne(int id) {
		return iGameRoleDAO.findById(id).get();
	}

	@Override
	public GameRole update(GameRole gameRole) {
		return iGameRoleDAO.save(gameRole);
	}

	@Override
	public void deleteOne(int id) {
		iGameRoleDAO.deleteById(id);
	}

	@Override
	public Page<GameRole> getPaginatedGameRole(Pageable pageable) {
		return iGameRoleDAO.findAll(pageable);
	}

	@Override
	public List<GameRole> findGameRoleByGameId(int idGame) {
		return iGameRoleDAO.findGameRoleByGameId(idGame);
	}

}
