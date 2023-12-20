package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="game_role")
public class GameRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String icon_url;
	
	@OneToMany
	@JoinColumn(name = "id")
	private List<GameGameRole> gameGameRole;
	
	public GameRole() {
		
	}

	public GameRole(int id, String name, String description, String icon_url) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon_url = icon_url;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	
	/**
	 * @return the gameGameRole
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "GameGameRole")
	public List<GameGameRole> getGameGameRole() {
		return gameGameRole;
	}
	
	/**
	 * @param gameGameRole the gameGameRole to set
	 */
	public void setGameGameRole(List<GameGameRole> gameGameRole) {
		this.gameGameRole = gameGameRole;
	}
}
