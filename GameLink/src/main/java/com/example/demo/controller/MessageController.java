package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Message;
import com.example.demo.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired(required = true)
	MessageService messageService;

	@GetMapping("/all")
	public List<Message> listAllMessages() {
		return messageService.getAll();
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

}
