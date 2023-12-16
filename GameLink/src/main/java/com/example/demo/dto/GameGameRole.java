package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "game_game_role")
public class GameGameRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_game")
	private Game idGame;

	@ManyToOne
	@JoinColumn(name = "id_game_role")
	private GameRole idGameRole;

	private int quantity;

	public GameGameRole() {

	}

	/**
	 * @param idGame
	 * @param idGameRole
	 * @param quantity
	 */
	public GameGameRole(Game idGame, GameRole idGameRole, int quantity) {
		this.idGame = idGame;
		this.idGameRole = idGameRole;
		this.quantity = quantity;
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
	 * @return the idGame
	 */
	public Game getIdGame() {
		return idGame;
	}

	/**
	 * @param idGame the idGame to set
	 */
	public void setIdGame(Game idGame) {
		this.idGame = idGame;
	}

	/**
	 * @return the idGameRole
	 */
	public GameRole getIdGameRole() {
		return idGameRole;
	}

	/**
	 * @param idGameRole the idGameRole to set
	 */
	public void setIdGameRole(GameRole idGameRole) {
		this.idGameRole = idGameRole;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
