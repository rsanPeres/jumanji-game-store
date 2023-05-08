package com.jumanji.games.service;

import com.jumanji.games.entity.Game;

import java.util.List;

public interface IGameService {
    Iterable<Game> getAll();
    Game getById(Long id);
    void insert(Game game, Long id);
    void update(Long id, Game game);
    void delete(Long id);
    boolean buy(Long id, int qtd);
    List<Game> favorites(Long playerId);
}
