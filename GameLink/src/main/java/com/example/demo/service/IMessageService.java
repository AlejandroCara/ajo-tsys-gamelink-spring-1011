package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.Message;

public interface IMessageService {

	// Metodos del CRUD
	public List<Message> getAll();
	
	public Page<Message> findByParty(Pageable pageable, int idParty);

	public Message add(Message message);

	public Message getOne(int id);

	public Message update(Message message);

	public void deleteOne(int id);
	
}
