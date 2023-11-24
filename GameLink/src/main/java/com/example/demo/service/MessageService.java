package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IMessageDAO;
import com.example.demo.dto.Message;

@Service
public class MessageService implements IMessageService {

	@Autowired(required = true)
	IMessageDAO iMessageDAO;

	@Override
	public List<Message> getAll() {
		return iMessageDAO.findAll();
	}

	@Override
	public Message add(Message message) {
		return iMessageDAO.save(message);
	}

	@Override
	public Message getOne(int id) {
		return iMessageDAO.findById(id).get();
	}

	@Override
	public Message update(Message message) {
		return iMessageDAO.save(message);
	}

	@Override
	public void deleteOne(int id) {
		iMessageDAO.deleteById(id);
	}

}
