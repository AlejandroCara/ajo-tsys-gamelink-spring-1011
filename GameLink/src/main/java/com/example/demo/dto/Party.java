package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "party")
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name = "max_players")
	private int maxPlayers;
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_game")
	private Game game;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User owner;

	@OneToMany
	@JoinColumn(name = "id_party")
	private List<UserPartyGameRole> userPartyGameRoles;

	@ManyToMany(mappedBy = "party")
	@JsonIgnoreProperties("party")
	private List<Tag> tag;

	public Party() {

	}

	public Party(int id, String name, int maxPlayers, String description, Game game, User owner) {
		super();
		this.id = id;
		this.name = name;
		this.maxPlayers = maxPlayers;
		this.description = description;
		this.game = game;
		this.owner = owner;
	}

	public Party(int id, String name, int maxPlayers, String description, Game game, User owner, List<Tag> tag) {
		super();
		this.id = id;
		this.name = name;
		this.maxPlayers = maxPlayers;
		this.description = description;
		this.game = game;
		this.owner = owner;
		this.tag = tag;
	}

	public Party(int id, String name, int maxPlayers, String description, Game game, List<UserPartyGameRole> userPartyGameRoles, User owner) {
		super();
		this.id = id;
		this.name = name;
		this.maxPlayers = maxPlayers;
		this.description = description;
		this.game = game;
		this.owner = owner;
		this.userPartyGameRoles = userPartyGameRoles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<UserPartyGameRole> getUserPartyGameRoles() {
		return userPartyGameRoles;
	}

	public void setUserPartyGameRoles(List<UserPartyGameRole> userPartyGameRoles) {
		this.userPartyGameRoles = userPartyGameRoles;
	}

	public List<Tag> getTag() {
		return tag;
	}

	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}

}
