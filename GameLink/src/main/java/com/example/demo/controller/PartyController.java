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

import com.example.demo.dto.Party;
import com.example.demo.service.PartyService;

@RestController
@RequestMapping("/party")
public class PartyController {

	@Autowired(required = true)
	PartyService partyService;

	@GetMapping("/all")
	public ResponseEntity<List<Party>> listAllParties(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<Party> partyPage = partyService.getPaginatedAllParty(PageRequest.of(page, size));
		List<Party> parties = partyPage.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());

		return new ResponseEntity<>(parties, HttpStatus.OK);
	}

	@GetMapping("/all/game/{idGame}")
	public ResponseEntity<List<Party>> listAllPartiesByGame(@PathVariable(name = "idGame") int idGame,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<Party> partyPage = partyService.getPaginatedAllFindByGame(PageRequest.of(page, size), idGame);
		List<Party> parties = partyPage.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());

		return new ResponseEntity<>(parties, HttpStatus.OK);
	}
	
	@GetMapping("/all/user/{idUser}")
	public ResponseEntity<List<Party>> listAllPartiesByUser(@PathVariable(name = "idUser") int idUser,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<Party> partyPage = partyService.getPaginateAllByUser(PageRequest.of(page, size), idUser);
		List<Party> parties = partyPage.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());

		return new ResponseEntity<>(parties, HttpStatus.OK);
	}
	
	@GetMapping("/all/filter")
	public ResponseEntity<List<Party>> listAllPartiesByUser(@RequestParam (name = "idTag", required = false) List<Integer> idTag) {
		List<Party> parties = partyService.getAllByTags(idTag);

		return new ResponseEntity<>(parties, HttpStatus.OK);
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

	private Party convertToDTO(Party party) {
		return new Party(
				party.getId(), 
				party.getName(), 
				party.getMaxPlayers(), 
				party.getDescription(), 
				party.getGame(),
				party.getOwner(),
				party.getTag());
	}
}
