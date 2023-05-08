package com.jumanji.games.service.imp;

import com.jumanji.games.entity.Client;
import com.jumanji.games.entity.Game;
import com.jumanji.games.entity.Player;
import com.jumanji.games.entity.Product;
import com.jumanji.games.repository.IClientRepository;
import com.jumanji.games.repository.IPlayerRepository;
import com.jumanji.games.repository.IProductRepository;
import com.jumanji.games.service.IGameService;
import com.jumanji.games.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImp implements IPlayerService {
    @Autowired
    private IPlayerRepository repository;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IGameService gameService;

    @Override
    public Iterable<Player> getAll() {
        return repository.findAll();
    }

    @Override
    public Player getById(Long id) {
        Optional<Player> player = repository.findById(id);
        return player.orElse(null);
    }

    @Override
    public void insert(Player client) {
        clientRepository.save(client);
    }

    @Override
    public void update(Player client, Long id) {
        Optional<Client> clientFound = clientRepository.findById(id);
        if(clientFound.isPresent()){
            System.out.println("id: " + id + ", clientId: " + clientFound.get().getId());
            clientFound.get().setPassword(client.getPassword());
            clientRepository.save(clientFound.get());
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Player> player = repository.findById(id);
        if(player.isPresent()){
            removeFromFavorite(player.get());
            removeFromShopping(player.get());
            repository.delete(player.get());
        }
    }

    @Override
    public List<Product> getShopping(Long clientId){
        return productRepository.getShoppingByClientId(clientId);
    }

    @Override
    public List<Game> getFavorite(Long playerId){
        return gameService.favorites(playerId);
    }

    @Override
    public void insertFavorite(Long gameId, Long playerId){
        Game game = gameService.getById(gameId);
        Optional<Player> player = repository.findById(playerId);
        if(game != null && player.isPresent()){
            player.get().setFavorite(game);
            repository.save(player.get());

            game.attach(player.get());
            gameService.insert(game, gameId);
        }
    }

    @Override
    public void insertShopping(Long prodId, Long playerId){
        Game game = gameService.getById(prodId);
        Optional<Player> player = repository.findById(playerId);
        if(game != null && player.isPresent()){
            player.get().setShopping(game);
            repository.save(player.get());

            game.attach(player.get());
            gameService.insert(game, prodId);
        }
    }

    @Override
    public void deleteFavorite(Player player, Long id){
        Game prod = gameService.getById(id);
        if(prod != null){
            removeFromFavorite(player);
            player.removeFavorite(prod);
            repository.save(player);
            gameService.insert(prod, prod.getId());
        }

    }

    @Override
    public void deleteShopping(Player player,Long id){
        Game prod = gameService.getById(id);
        if(prod != null){
            removeFromShopping(player);
            player.removeShopping(prod);
            repository.save(player);
            gameService.insert(prod, prod.getId());
        }
    }

    private void removeFromFavorite(Player player){
        player.getFavorites().forEach(x -> x.detach(player));
    }

    private void removeFromShopping(Player player){
        player.getShopping().forEach(x -> x.detach(player));
    }

    @Override
    public void buy(Product product, int qtd, Long playerId){
        Game game = gameService.getById(product.getId());
        Optional<Player> player = repository.findById(playerId);
        if(game != null && player.isPresent()){
            gameService.buy(game.getId(), qtd);
            deleteShopping(player.get(), product.getId());
        }

    }
}
