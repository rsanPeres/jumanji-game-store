package com.jumanji.games.controller.dto;

import com.jumanji.games.entity.Address;
import com.jumanji.games.entity.Player;

public class PlayerDto {
    public Long id;
    public String name;
    public String email;
    public String password;
    public String cep;

    public Player toEntity(){
        return new Player(name, email, password, new Address(cep));
    }
}

