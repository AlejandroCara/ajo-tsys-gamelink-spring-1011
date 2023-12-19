package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_party_game_role")
public class UserPartyGameRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "id_party")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Party party;
	
	@ManyToOne
	@JoinColumn(name = "id_game_role")
	private GameRole gameRole;
	
	public UserPartyGameRole() {
		
	}

	public UserPartyGameRole(int id, User user, Party party, GameRole gameRole) {
		super();
		this.id = id;
		this.user = user;
		this.party = party;
		this.gameRole = gameRole;
	}
	
	public UserPartyGameRole(User user, Party party, GameRole gameRole) {
		super();
		this.user = user;
		this.party = party;
		this.gameRole = gameRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public GameRole getGameRole() {
		return gameRole;
	}

	public void setGameRole(GameRole gameRole) {
		this.gameRole = gameRole;
	}
}
