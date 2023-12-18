package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "username")
	private String userName;
	@Hidden
	private String password;
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "id_role")
	private Role role;
	
	@OneToMany
	@JoinColumn(name = "id")
	@JsonIgnore
	private List<Event> event;
	
	@OneToMany
	@JoinColumn(name = "id_user")
	@JsonIgnore
	private List<UserPartyGameRole> userPartyGameRoles;
	
	public User() {
		
	}

	public User(int id, String userName, String password, String email, Role role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	public User(int id, String userName, String password, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

	public List<UserPartyGameRole> getUserPartyGameRoles() {
		return userPartyGameRoles;
	}

	public void setUserPartyGameRoles(List<UserPartyGameRole> userPartyGameRoles) {
		this.userPartyGameRoles = userPartyGameRoles;
	}
}
