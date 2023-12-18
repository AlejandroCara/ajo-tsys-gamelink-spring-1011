package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.GameRole;

public interface IGameRoleService {

	// Metodos del CRUD
	public List<GameRole> getAll();

	public GameRole add(GameRole gameRole);

	public GameRole getOne(int id);

	public GameRole update(GameRole gameRole);

	public void deleteOne(int id);
	
	Page<GameRole> getPaginatedGameRole(Pageable pageable);
	
	List<GameRole> findGameRoleByGameId(int idGame);
	

}
