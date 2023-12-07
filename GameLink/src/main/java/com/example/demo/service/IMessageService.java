package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.Message;

public interface IMessageService {

	// Metodos del CRUD
	public List<Message> getAll();
	
	public Page<Message> getPaginatedAllMessage(Pageable pageable);
	
	public Page<Message> getPaginatedAllParty(Pageable pageable, int idParty);
	
	public Page<Message> getPaginatedAllAuthor(Pageable pageable, int idAuthor);
	
	public Page<Message> getPaginatedAllPartyAndAuthor(Pageable pageable, int idParty, int idAuthor);

	public Message add(Message message);

	public Message getOne(int id);

	public Message update(Message message);

	public void deleteOne(int id);
	
}
