package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IEventDAO;
import com.example.demo.dto.Event;

@Service
public class EventService implements IEventService {

	@Autowired(required = true)
	IEventDAO iEventDAO;

	@Override
	public List<Event> getAll() {
		return iEventDAO.findAll();
	}

	@Override
	public Event add(Event event) {
		return iEventDAO.save(event);
	}

	@Override
	public Event getOne(int id) {
		return iEventDAO.findById(id).get();
	}

	@Override
	public Event update(Event event) {
		return iEventDAO.save(event);
	}

	@Override
	public void deleteOne(int id) {
		iEventDAO.deleteById(id);
	}

	@Override
	public List<Event> findByGameId(int idGame) {
		return iEventDAO.findByGameId(idGame);
	}

}
