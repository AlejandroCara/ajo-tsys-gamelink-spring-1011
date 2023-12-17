package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.Party;
import com.example.demo.dto.User;

import jakarta.persistence.Tuple;

public interface IPartyDAO extends JpaRepository<Party, Integer> {

	List<Party> findByGameId(int idGame);

	Page<Party> findByGameId(int idGame, Pageable pageable);
	
	List<Party> findByOwnerId(int idUser);
	
	Page<Party> findByOwnerId(int idUser, Pageable pageable);
	
	List<Party> findByTagIdIn(List<Integer> idTags);
	
	Page<Party> findByTagIdIn(List<Integer> idTags, Pageable pageable);
	
	Page<Party> findByGameIdAndTagIdIn(int idGame, List<Integer> idTags, Pageable pageable);
	
	@Query(
		value = "SELECT u.* "
			  + "FROM user as u inner join user_party_game_role as upgr on  u.id = upgr.id_user "
			  + "WHERE upgr.id_party = ?1", nativeQuery = true)
	List<Tuple> findUsersByPartyId(int idParty);
}
