package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.GameRole;

public interface IGameRoleDAO extends JpaRepository<GameRole, Integer>{
	
	@Query(
		value = "SELECT gr.* "
			  + "FROM game_role as gr inner join game_game_role as ggr on  gr.id = ggr.id_game_role "
			  + "WHERE ggr.id_game = ?1", nativeQuery = true)
	List<GameRole> findGameRoleByGameId(int idGame);
}
