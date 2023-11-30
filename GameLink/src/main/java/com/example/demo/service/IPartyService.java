package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.Party;

public interface IPartyService {

	// Metodos del CRUD
	public List<Party> getAll();

	public Page<Party> getPaginatedAllParty(Pageable pageable);
	
	public List<Party> getAllByGame(int idGame);
	
	public Page<Party> getPaginatedAllFindByGame(Pageable pageable, int idGame);
	
	public List<Party> getAllByUser(int idUser);

	public Party add(Party party);

	public Party getOne(int id);

	public Party update(Party party);

	public void deleteOne(int id);

}
