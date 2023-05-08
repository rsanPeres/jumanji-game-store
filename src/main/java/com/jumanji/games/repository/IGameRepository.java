package com.jumanji.games.repository;

import com.jumanji.games.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT p.favorites FROM Player p WHERE p.id = ?1")
    public List<Game> getByPlayerId(Long playerId);

}
