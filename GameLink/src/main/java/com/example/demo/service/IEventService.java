package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Event;

public interface IEventService {

	// Metodos del CRUD
	public List<Event> getAll();

	public Event add(Event event);

	public Event getOne(int id);

	public Event update(Event event);

	public void deleteOne(int id);

}
