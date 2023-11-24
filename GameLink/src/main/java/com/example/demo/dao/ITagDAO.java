package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Tag;

public interface ITagDAO extends JpaRepository<Tag, Integer>{

}
