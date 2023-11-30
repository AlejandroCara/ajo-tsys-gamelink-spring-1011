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
		// TODO Auto-generated method stub
		return iPartyDAO.findAll(pageable);
	}
	
	@Override
	public Page<Party> getPaginatedAllFindByGame(Pageable pageable, int idGame) {
		return iPartyDAO.findByGameId(idGame, pageable);
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
