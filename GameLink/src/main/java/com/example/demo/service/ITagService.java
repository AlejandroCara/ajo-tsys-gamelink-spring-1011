package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.Tag;

public interface ITagService {

	// Metodos del CRUD
	public List<Tag> getAll();
	
	public Page<Tag> getPaginateAll(Pageable pageable);

	public Tag add(Tag tag);

	public Tag getOne(int id);

	public Tag update(Tag tag);

	public void deleteOne(int id);
}
