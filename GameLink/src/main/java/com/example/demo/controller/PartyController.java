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

import com.example.demo.dto.Party;
import com.example.demo.service.PartyService;

@RestController
@RequestMapping("/party")
public class PartyController {
	
	@Autowired(required = true)
	PartyService partyService;
	
	@GetMapping("/all")
	public List<Party> listAllParties() {
		return partyService.getAll();
	}

	@PostMapping("/add")
	public Party saveParty(@RequestBody Party party) {
		return partyService.add(party);
	}

	@GetMapping("/{id}")
	public Party getOneParty(@PathVariable(name = "id") int id) {
		return partyService.getOne(id);
	}

	@PutMapping("/{id}")
	public Party updateParty(@PathVariable(name = "id") int id, @RequestBody Party party) {

		Party preParty = new Party();
		Party newParty = new Party();

		preParty = partyService.getOne(id);

		preParty.setName(party.getName());
		preParty.setDescription(party.getDescription());
		preParty.setMaxPlayers(party.getMaxPlayers());

		newParty = partyService.update(preParty);

		return newParty;
	}

	@DeleteMapping("/{id}")
	public void deleteParty(@PathVariable(name = "id") int id) {
		partyService.deleteOne(id);
	}
}
