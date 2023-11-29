/**
 * 
 */
package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "game")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(nullable = false, length = 150)
	private String title;

	@Column(name = "thumbnail_url", nullable = false, length = 255)
	private String thumbnailUrl;

	@Column(nullable = false, length = 50)
	private String url;
	
	@OneToMany
	@JoinColumn(name = "id")
	@JsonIgnore
	private List<Event> event;
	
	@OneToMany
	@JoinColumn(name = "id")
	private List<Party> party;

	/** 
	 *  
	 */
	public Game() {

	}

	/**
	 * @param title
	 * @param thumbnailUrl
	 * @param url
	 */
	public Game(String title, String thumbnailUrl, String url) {
		this.title = title;
		this.thumbnailUrl = thumbnailUrl;
		this.url = url;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the thumbnailUrl
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	/**
	 * @param thumbnailUrl the thumbnailUrl to set
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the party
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Party")
	public List<Party> getParty() {
		return party;
	}
	
	/**
	 * @param party the party to set
	 */
	public void setParty(List<Party> party) {
		this.party = party;
	}

}
