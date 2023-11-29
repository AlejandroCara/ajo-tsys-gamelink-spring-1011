package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Message;

public interface IMessageService {

	// Metodos del CRUD
	public List<Message> getAll();

	public Message add(Message message);

	public Message getOne(int id);

	public Message update(Message message);

	public void deleteOne(int id);
}
