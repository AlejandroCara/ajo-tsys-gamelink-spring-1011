package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IPartyDAO;
import com.example.demo.dto.Party;

@Service
public class PartyService implements IPartyService {

	@Autowired(required = true)
	IPartyDAO iPartyDAO;

	@Override
	public List<Party> getAll() {
		return iPartyDAO.findAll();
	}
	
	@Override
	public Page<Party> getPaginatedAllParty(Pageable pageable) {
		return iPartyDAO.findAll(pageable);
	}
	
	@Override
	public List<Party> getAllByGame(int idGame) {
		return iPartyDAO.findByGameId(idGame);
	}
	
	@Override
	public Page<Party> getPaginatedAllFindByGame(Pageable pageable, int idGame) {
		return iPartyDAO.findByGameId(idGame, pageable);
	}

	@Override
	public List<Party> getAllByUser(int idUser) {
		return iPartyDAO.findByOwnerId(idUser);
	}
	
	@Override
	public Page<Party> getPaginateAllByUser(Pageable pageable, int idUser) {
		return iPartyDAO.findByOwnerId(idUser, pageable);
	}
	
	@Override
	public List<Party> getAllByTags(List<Integer> idTags) {
		return iPartyDAO.findByTagIdIn(idTags);
	}
	
	@Override
	public Page<Party> getPaginateAllByTags(Pageable pageable, List<Integer> idTags) {
		return iPartyDAO.findByTagIdIn(idTags, pageable);
	}
	
	@Override
	public Page<Party> getPaginateAllByGameAndByTags(Pageable pageable, int idGame, List<Integer> idTags) {
		return iPartyDAO.findByGameIdAndTagIdIn(idGame, idTags, pageable);
	}

	@Override
	public Party add(Party party) {
		return iPartyDAO.save(party);
	}

	@Override
	public Party getOne(int id) {
		return iPartyDAO.findById(id).get();
	}

	@Override
	public Party update(Party party) {
		return iPartyDAO.save(party);
	}

	@Override
	public void deleteOne(int id) {
		iPartyDAO.deleteById(id);
	}

}
