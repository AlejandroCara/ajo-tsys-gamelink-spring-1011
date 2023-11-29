package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity(name = "tag")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(nullable = false, length = 20)
	private String name;

	@Column(length = 255)
	private String description;
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "party_tag",
        joinColumns = { @JoinColumn(name = "id_tag") },
        inverseJoinColumns = { @JoinColumn(name = "id_party") }
    )
	@JsonIgnoreProperties("tag")
	private List<Party> party = new ArrayList<>();
	
	public Tag() {
			
	}
	
	public Tag(int id, String name, String description, List<Party> party) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.party = party;
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

	public List<Party> getParty() {
		return party;
	}

	public void setParty(List<Party> party) {
		this.party = party;
	}
	
	
}
