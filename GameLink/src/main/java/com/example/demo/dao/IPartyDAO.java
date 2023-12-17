package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Party;

public interface IPartyDAO extends JpaRepository<Party, Integer> {

	List<Party> findByGameId(int idGame);

	Page<Party> findByGameId(int idGame, Pageable pageable);
	
	List<Party> findByOwnerId(int idUser);
	
	Page<Party> findByOwnerId(int idUser, Pageable pageable);
	
	List<Party> findByTagIdIn(List<Integer> idTags);
	
	Page<Party> findByTagIdIn(List<Integer> idTags, Pageable pageable);
	
	Page<Party> findByGameIdAndTagIdIn(int idGame, List<Integer> idTags, Pageable pageable);
	
}
