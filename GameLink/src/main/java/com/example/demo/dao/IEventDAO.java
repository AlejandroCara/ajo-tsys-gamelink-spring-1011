package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Event;

public interface IEventDAO extends JpaRepository<Event, Integer> {

	List<Event> findByGameId(int idGame);

	Page<Event> findByGameId(Pageable pageable, int idGame);
}
