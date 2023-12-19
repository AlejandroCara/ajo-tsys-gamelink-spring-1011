package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import com.example.demo.dto.GameRole;
import com.example.demo.dto.Party;
import com.example.demo.dto.User;
import com.example.demo.dto.UserPartyGameRole;
import com.example.demo.security.GameLinkUserDetails;
import com.example.demo.service.GameRoleService;
import com.example.demo.service.PartyService;
import com.example.demo.service.UserPartyGameRoleService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/party")
public class PartyController {

	@Autowired(required = true)
	PartyService partyService;

	@Autowired(required = true)
	UserService userService;

	@Autowired(required = true)
	UserPartyGameRoleService userPartyGameRoleService;

	@Autowired(required = true)
	GameRoleService gameRoleService;

	@GetMapping("/all")
	public ResponseEntity<Page<Party>> listAllParties(@RequestParam(name = "idGame", required = false) Integer idGame,
			@RequestParam(name = "idTag", required = false) List<Integer> idTag,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Page<Party> partyPage = null;

		if (idGame != null && idTag != null) {
			partyPage = partyService.getPaginateAllByGameAndByTags(PageRequest.of(page, size), idGame, idTag);
		} else if (idGame != null) {
			partyPage = partyService.getPaginatedAllFindByGame(PageRequest.of(page, size), idGame);
		} else if (idTag != null) {
			partyPage = partyService.getPaginateAllByTags(PageRequest.of(page, size), idTag);
		} else {
			partyPage = partyService.getPaginatedAllParty(PageRequest.of(page, size));
		}
		
		return new ResponseEntity<>(partyPage, HttpStatus.OK);
	}

	// Return a list of the parties owned by the user with the id passed by path
	// variable
	@GetMapping("/all/user/{idUser}")
	public ResponseEntity<Page<Party>> listAllPartiesByUser(@PathVariable(name = "idUser") int idUser,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<Party> partyPage = partyService.getPaginateAllByUser(PageRequest.of(page, size), idUser);

		return new ResponseEntity<>(partyPage, HttpStatus.OK);
	}

	// The logged user is added to the party passed by path variable if its not
	// already in that party
	@PostMapping("/join/{id}")
	public ResponseEntity joinParty(Authentication authentication, @PathVariable(name = "id") int id) {
		GameLinkUserDetails ud = (GameLinkUserDetails) authentication.getPrincipal();
		User user = userService.getOneById(ud.getId());
		Party party = partyService.getOne(id);
		GameRole gameRole = gameRoleService.getOne(3);
		boolean isAlreadyMember = false;

		for (UserPartyGameRole member : party.getUserPartyGameRoles()) {
			if (member.getUser().getId() == user.getId()) {
				isAlreadyMember = true;
			}
		}

		if (!isAlreadyMember) {
			userPartyGameRoleService.add(new UserPartyGameRole(user, party, gameRole));
		} else {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	// The logged user is removed to from the party passed by path variable if its
	// in the party
	@PostMapping("/leave/{id}")
	public ResponseEntity leaveParty(Authentication authentication, @PathVariable(name = "id") int id) {
		GameLinkUserDetails ud = (GameLinkUserDetails) authentication.getPrincipal();
		User user = userService.getOneById(ud.getId());
		Party party = partyService.getOne(id);
		GameRole gameRole = gameRoleService.getOne(3);
		boolean isMember = false;
		int userPartyGameRoleId = 0;

		for (UserPartyGameRole member : party.getUserPartyGameRoles()) {
			if (member.getUser().getId() == user.getId()) {
				isMember = true;
				userPartyGameRoleId = member.getId();
			}
		}

		if (isMember) {
			userPartyGameRoleService.deleteOne(userPartyGameRoleId);
		} else {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	// Return a list of the parties owned by the logged user contained in the token
	@GetMapping("/own")
	public ResponseEntity<Page<Party>> listOwnParties(Authentication authentication,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		GameLinkUserDetails ud = (GameLinkUserDetails) authentication.getPrincipal();
		Page<Party> partyPage = partyService.getPaginateAllByUser(PageRequest.of(page, size), ud.getId());

		return new ResponseEntity<>(partyPage, HttpStatus.OK);
	}

	// Saves the party from the body of the request with the logged user as owner
	@PostMapping("/add")
	public Party saveParty(Authentication authentication, @RequestBody Party party) {
		GameLinkUserDetails ud = (GameLinkUserDetails) authentication.getPrincipal();
		party.setOwner(userService.getOneById(ud.getId()));
		Party savedParty = partyService.add(party);
		//Creates intermediate table rows to know which roles are needed in the party
		for(int i = 0; i < savedParty.getUserPartyGameRoles().size(); i++) {
			savedParty.getUserPartyGameRoles().get(i).setParty(savedParty);
			userPartyGameRoleService.add(savedParty.getUserPartyGameRoles().get(i));
		}
		return savedParty;
	}

	// Return a party with the same id passed by path variable
	@GetMapping("/id/{id}")
	public Party getOneParty(@PathVariable(name = "id") int id) {
		return partyService.getOne(id);
	}

	// Saves the changes of the party from request's body
	@PutMapping("/{id}")
	public Party updateParty(@PathVariable(name = "id") int id, @RequestBody Party party) {

		Party preParty = new Party();
		Party newParty = new Party();

		preParty = partyService.getOne(id);

		preParty.setName(party.getName());
		preParty.setDescription(party.getDescription());
		preParty.setMaxPlayers(party.getMaxPlayers());
		preParty.setOwner(party.getOwner());

		newParty = partyService.update(preParty);

		return newParty;
	}

	// Saves the changes of the party from request's body if is sent by its owner
	@PutMapping("/own/update/{id}")
	public ResponseEntity updateOwnParty(Authentication authentication, @PathVariable(name = "id") int id,
			@RequestBody Party party) {

		Party preParty = new Party();

		GameLinkUserDetails ud = (GameLinkUserDetails) authentication.getPrincipal();
		preParty = partyService.getOne(id);
		System.out.println(preParty.getOwner().getId());
		if (preParty.getOwner().getId() == ud.getId()) {
			preParty.setName(party.getName());
			preParty.setDescription(party.getDescription());
			preParty.setMaxPlayers(party.getMaxPlayers());

			partyService.update(preParty);
		} else {
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	// Delete the party with the id passed by path variable if the request is sent
	// by its owner
	@DeleteMapping("/own/delete/{id}")
	public ResponseEntity deleteOwnParty(Authentication authentication, @PathVariable(name = "id") int id) {
		GameLinkUserDetails ud = (GameLinkUserDetails) authentication.getPrincipal();
		if (ud.getId() == partyService.getOne(id).getOwner().getId()) {
			partyService.deleteOne(id);
		} else {
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	// Delete the party with the id passed by path variable
	@DeleteMapping("/delete/{id}")
	public void deleteParty(Authentication authentication, @PathVariable(name = "id") int id) {
		partyService.deleteOne(id);
	}

	@GetMapping("/members/{id}")
	public List<UserPartyGameRole> getMembers(@PathVariable(name = "id") int id) {
		System.out.println(partyService.getOne(id).getUserPartyGameRoles());
		return partyService.getOne(id).getUserPartyGameRoles();
	}

}
