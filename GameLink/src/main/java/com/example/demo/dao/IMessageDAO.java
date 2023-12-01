package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Message;

public interface IMessageDAO extends JpaRepository<Message, Integer>{

	Page<Message> findByPartyId(int idParty, Pageable pageable);
	
	Page<Message> findByAuthorId(int idAuthor, Pageable pageable);
	
}

