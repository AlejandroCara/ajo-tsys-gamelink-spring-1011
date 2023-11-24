package com.example.demo.dto;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(nullable = false, length = 255)
	private String message;
	
	@Column(nullable = false)
	private Date created_at;
	
	private Date updated_at;
	
	@ManyToOne
	@JoinColumn(name = "author", nullable = false)
	private User author;

	@ManyToOne
	@JoinColumn(name = "id_party", nullable = false)
	private Party idParty;

	public Message() {
		
	}

	public Message(int id, String message, Date created_at, Date updated_at, User author, Party idParty) {
		this.id = id;
		this.message = message;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.author = author;
		this.idParty = idParty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Party getIdParty() {
		return idParty;
	}

	public void setIdParty(Party idParty) {
		this.idParty = idParty;
	}
	
}
