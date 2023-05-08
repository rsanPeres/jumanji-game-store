package com.jumanji.games.service.imp;

import com.jumanji.games.entity.Game;
import com.jumanji.games.entity.Product;
import com.jumanji.games.repository.IGameRepository;
import com.jumanji.games.service.IGameService;
import com.jumanji.games.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImp implements IGameService {
    @Autowired
    private IGameRepository repository;
    @Autowired
    private IProductService productService;

    @Override
    public Iterable<Game> getAll() {
        return repository.findAll();
    }

    @Override
    public Game getById(Long id) {
        Optional<Game> game = repository.findById(id);
        return game.orElse(null);
    }

    @Override
    public void insert(Game game, Long id) {
        Product prod = productService.getById(id);
        if(prod != null){
            game.setId(id);
            repository.save(game);
        }
    }

    @Override
    public void update(Long id, Game game) {
        Product prod = productService.getById(id);
        if(prod != null){
            prod.setPrice(game.getPrice());
            productService.insert(prod);
        }
    }

    @Override
    public void delete(Long id) {
        Product prod = productService.getById(id);
        Optional<Game> game = repository.findById(id);
        if(prod != null && game.isPresent()){
            repository.delete(game.get());
            productService.delete(id);
        }
    }

    @Override
    public boolean buy(Long id, int qtd){
        Optional<Game> product = repository.findById(id);
        if(product.isPresent()){
            if(product.get().removeFromStock(qtd)){
                product.get().setAmount(product.get().getAmount() - qtd);
                repository.save(product.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Game> favorites(Long playerId){
        return repository.getByPlayerId(playerId);
    }

}
