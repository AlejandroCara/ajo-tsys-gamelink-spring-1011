package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Event;

public interface IEventDAO extends JpaRepository<Event, Integer>{

}
