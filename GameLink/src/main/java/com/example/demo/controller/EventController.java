package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Event>> listAllEvents(@RequestParam(required = false) Integer idGame,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Page<Event> eventPage = null;

		if (idGame != null) {
			eventPage = eventService.getPaginatedEventByGameId(PageRequest.of(page, size), idGame);
		} else {
			eventPage = eventService.getPaginatedEvent(PageRequest.of(page, size));
		}

		List<Event> result = eventPage.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/add")
	public Event saveEvent(@RequestBody Event event) {
		return eventService.add(event);
	}

	@GetMapping("/id/{id}")
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

	private Event convertToDTO(Event event) {
		return new Event(event.getId(), event.getName(), event.getDescription(), event.getStatus(), event.getStart(),
				event.getEnd(), event.getIdGame(), event.getIdUser());
	}

}
