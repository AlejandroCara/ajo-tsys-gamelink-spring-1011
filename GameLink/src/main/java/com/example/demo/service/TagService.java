package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ITagDAO;
import com.example.demo.dto.Tag;

@Service
public class TagService implements ITagService{
	
	@Autowired(required = true)
	ITagDAO iTagDAO;

	@Override
	public List<Tag> getAll() {
		return iTagDAO.findAll();
	}

	@Override
	public Tag add(Tag tag) {
		return iTagDAO.save(tag);
	}

	@Override
	public Tag getOne(int id) {
		return iTagDAO.findById(id).get();
	}

	@Override
	public Tag update(Tag tag) {
		return iTagDAO.save(tag);
	}

	@Override
	public void deleteOne(int id) {
		iTagDAO.deleteById(id);
	}

	@Override
	public Page<Tag> getPaginateAll(Pageable pageable) {
		return iTagDAO.findAll(pageable);
	}

}
