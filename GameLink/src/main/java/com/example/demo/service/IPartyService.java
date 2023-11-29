package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Party;

public interface IPartyService {

	// Metodos del CRUD
	public List<Party> getAll();

	public Party add(Party party);

	public Party getOne(int id);

	public Party update(Party party);

	public void deleteOne(int id);
}
