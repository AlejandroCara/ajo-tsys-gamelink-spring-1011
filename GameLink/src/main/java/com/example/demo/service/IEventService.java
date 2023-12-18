package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.Event;

public interface IEventService {

	// Metodos del CRUD
	public List<Event> getAll();

	public Event add(Event event);

	public Event getOne(int id);

	public Event update(Event event);

	public void deleteOne(int id);

	public List<Event> findByGameId(int idGame);

	public Page<Event> getPaginatedEvent(Pageable pageable);

	public Page<Event> getPaginatedEventByGameId(Pageable pageable, int idGame);
}
