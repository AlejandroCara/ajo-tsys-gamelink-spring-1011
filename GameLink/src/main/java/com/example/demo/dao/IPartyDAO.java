package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Party;

public interface IPartyDAO extends JpaRepository<Party, Integer>{

	List<Party> findByGameId(int idGame);

	Page<Party> findByGameId(int idGame, Pageable pageable);
}
