package com.jumanji.games.controller;

import com.jumanji.games.controller.Response.PlayerResponse;
import com.jumanji.games.controller.dto.PlayerDto;
import com.jumanji.games.entity.Client;
import com.jumanji.games.entity.Game;
import com.jumanji.games.entity.Player;
import com.jumanji.games.entity.Product;
import com.jumanji.games.service.IClientService;
import com.jumanji.games.service.IGameService;
import com.jumanji.games.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/players")
public class PlayerController {
    @Autowired
    private IPlayerService service;
    @Autowired
    private  IClientService clientService;
    @Autowired
    private IGameService gameService;

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> getAll(){
        Iterable<Client> clients = clientService.getAll();
        List<PlayerResponse> clientResponses = new ArrayList<>();
        for (Client client : clients) {
            PlayerResponse clientResponse = new PlayerResponse(
                    client.getId(),
                    client.getName(),
                    client.getEmail(),
                    client.getAddress().getCep()
            );
            clientResponses.add(clientResponse);
        }
        return ResponseEntity.ok(clientResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getById(@PathVariable Long id){
        Player player = service.getById(id);

        return ResponseEntity.ok(new PlayerResponse(player.getId(), player.getName(), player.getEmail(), player.getAddress().getCep()));
    }

    @GetMapping("/shopping/{id}")
    public ResponseEntity<List<Product>> Shopping(@PathVariable Long id){
        return ResponseEntity.ok(service.getShopping(id));
    }

    @GetMapping("/favorites/{id}")
    public ResponseEntity<List<Game>> FavoritesPlayer(@PathVariable Long id){
        return ResponseEntity.ok(service.getFavorite(id));
    }

    @PostMapping
    public ResponseEntity<PlayerDto> insertPlayer(@RequestBody PlayerDto dto){
        Player player = dto.toEntity();
        clientService.setClientAddress(player);
        service.insert(player);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/favorites/{gameId}")
    public ResponseEntity<String> addFavorites(@PathVariable Long gameId, @RequestParam(value = "playerId") Long playerId){
        service.insertFavorite(gameId, playerId);
        return ResponseEntity.ok("Inserido");
    }

    @PostMapping("/shopping/{gameId}")
    public ResponseEntity<String> addShopping(@PathVariable Long gameId, @RequestParam(value = "playerId") Long playerId){
        service.insertShopping(gameId, playerId);
        return ResponseEntity.ok("Inserido");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDto> update(@PathVariable Long id, @RequestBody PlayerDto dto){
        Player player = dto.toEntity();
        clientService.setClientAddress(player);
        service.update(player, id);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/game/{gameId}")
    public ResponseEntity<String> buy(
            @PathVariable Long gameId,
            @RequestParam(value = "playerId") Long playerId,
            @RequestParam(value = "qtd") Integer qtd){
        Game game = gameService.getById(gameId);
        service.buy(game, qtd, playerId);
        return ResponseEntity.ok("Operação realizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Deletado");
    }

    @DeleteMapping("/favorites/{gameId}")
    public ResponseEntity<String> deleteFavorites(@PathVariable Long gameId, @RequestParam(value = "playerId") Long playerId){
        Player player = service.getById(playerId);
        service.deleteFavorite(player,gameId);
        return ResponseEntity.ok("Deletado");
    }

    @DeleteMapping("/shopping/{gameId}")
    public ResponseEntity<String> deleteShopping(@PathVariable Long gameId, @RequestParam(value = "playerId") Long playerId){
        Player player = service.getById(playerId);
        service.deleteShopping(player,gameId);
        return ResponseEntity.ok("Deletado");
    }
}