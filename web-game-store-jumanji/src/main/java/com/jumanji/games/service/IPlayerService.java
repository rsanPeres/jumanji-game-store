package com.jumanji.games.service;

import com.jumanji.games.entity.Game;
import com.jumanji.games.entity.Player;
import com.jumanji.games.entity.Product;

import java.util.List;

public interface IPlayerService {
    Iterable<Player> getAll();
    Player getById(Long id);
    void insert(Player client);
    void update(Player client, Long id);
    void delete(Long id);
    List<Product> getShopping(Long clientId);
    List<Game> getFavorite(Long playerId);
    void insertFavorite(Long gameId, Long playerId);
    void insertShopping(Long prodId, Long playerId);
    void deleteFavorite(Player player, Long id);
    void deleteShopping(Player player,Long id);
    void buy(Product product, int qtd, Long playerId);
}
