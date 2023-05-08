package com.jumanji.games.controller.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GameResponse {
    public Long id;
    public String name;
    public BigDecimal price;
    public LocalDateTime arrival;

    public GameResponse(Long id, String name, BigDecimal price, LocalDateTime arrival){
        this.id = id;
        this.name = name;
        this.price = price;
        this.arrival = arrival;
    }
}
