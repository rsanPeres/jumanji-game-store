package com.jumanji.games.controller.dto;

import com.jumanji.games.entity.Game;
import com.jumanji.games.entity.enumerable.GameType;
import com.jumanji.games.entity.enumerable.ProductType;

import java.math.BigDecimal;

public class GameDto {
    public Long id;
    public String name;
    public BigDecimal price;
    public Integer amount;
    public ProductType type;
    public GameType typeGame;

    public Game toEntity(){
        return new Game(this.name, this.price, this.amount, this.typeGame, this.type);
    }
}


