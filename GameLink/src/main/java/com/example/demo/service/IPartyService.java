package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.Party;
import com.example.demo.dto.User;

import jakarta.persistence.Tuple;

public interface IPartyService {

	// Metodos del CRUD
	public List<Party> getAll();

	public Page<Party> getPaginatedAllParty(Pageable pageable);
	
	public List<Party> getAllByGame(int idGame);
	
	public Page<Party> getPaginatedAllFindByGame(Pageable pageable, int idGame);
	
	public List<Party> getAllByUser(int idUser);
	
	public Page<Party> getPaginateAllByUser(Pageable pageable, int idUser);
	
	public List<Party> getAllByTags(List<Integer> idTags);

	public Page<Party> getPaginateAllByTags(Pageable pageable, List<Integer> idTags);
	
	public Page<Party> getPaginateAllByGameAndByTags(Pageable pageable, int idGame, List<Integer> idTags);
	
	public Party add(Party party);

	public Party getOne(int id);

	public Party update(Party party);

	public void deleteOne(int id);
	
	public List<Tuple> getMembers(int idParty);
}
