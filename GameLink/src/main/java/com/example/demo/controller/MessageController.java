package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Message;
import com.example.demo.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired(required = true)
	MessageService messageService;

	@GetMapping("/all")
	public ResponseEntity<List<Message>> listAllMessages(
			@RequestParam(name = "idParty", required = false) Integer idParty,
			@RequestParam(name = "idUser", required = false) Integer idUser, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<Message> messagePage = null;

		if (idParty != null && idUser != null) {
			messagePage = messageService.getPaginatedAllPartyAndAuthor(PageRequest.of(page, size), idParty, idUser);
		} else if (idParty != null) {
			messagePage = messageService.getPaginatedAllParty(PageRequest.of(page, size), idParty);
		} else if (idUser != null) {
			messagePage = messageService.getPaginatedAllAuthor(PageRequest.of(page, size), idUser);
		} else {
			messagePage = messageService.getPaginatedAllMessage(PageRequest.of(page, size));
		}

		List<Message> messages = messagePage.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());

		return new ResponseEntity<>(messages, HttpStatus.OK);
	}

	@PostMapping("/add")
	public Message saveMessage(@RequestBody Message message) {
		return messageService.add(message);
	}

	@GetMapping("/{id}")
	public Message getOneMessage(@PathVariable(name = "id") int id) {
		return messageService.getOne(id);
	}

	@PutMapping("/{id}")
	public Message updateMessage(@PathVariable(name = "id") int id, @RequestBody Message message) {

		Message prevMessage = new Message();
		Message newMessage = new Message();

		prevMessage = messageService.getOne(id);

		prevMessage.setMessage(message.getMessage());
		prevMessage.setCreated_at(message.getCreated_at());
		prevMessage.setUpdated_at(message.getUpdated_at());
		prevMessage.setAuthor(message.getAuthor());
		prevMessage.setIdParty(message.getIdParty());

		newMessage = messageService.update(prevMessage);

		return newMessage;
	}

	@DeleteMapping("/{id}")
	public void deleteMessage(@PathVariable(name = "id") int id) {
		messageService.deleteOne(id);
	}

	private Message convertToDTO(Message message) {
		return new Message(message.getId(), message.getMessage(), message.getCreated_at(), message.getUpdated_at(),
				message.getAuthor(), message.getIdParty());
	}

}
