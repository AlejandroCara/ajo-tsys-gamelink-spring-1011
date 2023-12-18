// Generated with g9.

package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(length = 255)
	private String description;

	@Column(nullable = false, length = 100)
	private String status;

	@Column(nullable = false)
	private LocalDate start;

	@Column(nullable = false)
	private LocalDate end;

	@ManyToOne
	@JoinColumn(name = "id_game", nullable = false)
	private Game game;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User idUser;

	/**
	 * Default constructor.
	 */
	public Event() {
	}

	/**
	 * @param name
	 * @param description
	 * @param status
	 * @param start
	 * @param end
	 * @param idGame
	 * @param idUser
	 */
	public Event(String name, String description, String status, LocalDate start, LocalDate end, Game idGame,
			User idUser) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.start = start;
		this.end = end;
		this.game = idGame;
		this.idUser = idUser;
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param status
	 * @param start
	 * @param end
	 * @param game
	 * @param idUser
	 */
	public Event(int id, String name, String description, String status, LocalDate start, LocalDate end, Game game,
			User idUser) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.start = start;
		this.end = end;
		this.game = game;
		this.idUser = idUser;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the start
	 */
	public LocalDate getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(LocalDate start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public LocalDate getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(LocalDate end) {
		this.end = end;
	}

	/**
	 * @return the idGame
	 */
	public Game getIdGame() {
		return game;
	}

	/**
	 * @param idGame the idGame to set
	 */
	public void setIdGame(Game idGame) {
		this.game = idGame;
	}

	/**
	 * @return the idUser
	 */
	public User getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

}
