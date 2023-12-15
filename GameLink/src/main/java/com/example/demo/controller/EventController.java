package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Event;
import com.example.demo.service.EventService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired(required = true)
	EventService eventService;

	@GetMapping("/all")
	public List<Event> listAllEvents(@RequestParam(required = false) Integer idGame) {
		
		List<Event> result;
		
		if (idGame != null) {
			result = eventService.findByGameId(idGame);
		} else {
			result = eventService.getAll();
		}
		
		return result;
	}

	@PostMapping("/add")
	public Event saveEvent(@RequestBody Event event) {
		return eventService.add(event);
	}

	@GetMapping("/{id}")
	public Event getOneEvent(@PathVariable(name = "id") int id) {
		return eventService.getOne(id);
	}

	@PutMapping("/{id}")
	public Event updateEvent(@PathVariable(name = "id") int id, @RequestBody Event event) {

		Event prevEvent = new Event();
		Event newEvent = new Event();

		prevEvent = eventService.getOne(id);

		prevEvent.setName(event.getName());
		prevEvent.setDescription(event.getDescription());
		prevEvent.setStatus(event.getStatus());
		prevEvent.setStart(event.getStart());
		prevEvent.setEnd(event.getEnd());
		prevEvent.setIdGame(event.getIdGame());
		prevEvent.setIdUser(event.getIdUser());

		newEvent = eventService.update(prevEvent);

		return newEvent;
	}

	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable(name = "id") int id) {
		eventService.deleteOne(id);
	}

}
