package com.jumanji.games.controller;

import com.jumanji.games.controller.Response.GameResponse;
import com.jumanji.games.controller.dto.GameDto;
import com.jumanji.games.entity.Product;
import com.jumanji.games.service.IGameService;
import com.jumanji.games.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/games")
public class GameController {
    @Autowired
    private IGameService service;
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAll(){
        Iterable<Product> products = productService.getAll();
        List<GameResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            GameResponse productResponse = new GameResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getArrival()
            );
            productResponses.add(productResponse);
        }
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getById(@PathVariable Long id){
        Product product = productService.getById(id);

        return ResponseEntity.ok(new GameResponse( product.getId(),
                product.getName(),
                product.getPrice(),
                product.getArrival()));
    }

    @PostMapping
    public ResponseEntity<GameDto> insert(@RequestBody GameDto dto){
        Product product = dto.toEntity();
        productService.insert(product);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDto> update(@PathVariable Long id, @RequestBody GameDto dto){
        Product product = dto.toEntity();
        productService.update(id, product);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok("Deletado");
    }
}
