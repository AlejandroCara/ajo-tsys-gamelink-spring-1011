package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.GameGameRole;

public interface IGameGameRoleService {

	// Metodos del CRUD
	public List<GameGameRole> getAll();

	public GameGameRole add(GameGameRole gameGameRole);

	public GameGameRole getOne(int id);

	public GameGameRole update(GameGameRole gameGameRole);

	public void deleteOne(int id);

}