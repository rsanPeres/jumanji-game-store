package com.jumanji.games.controller.Response;

public class PlayerResponse {
    public Long id;
    public String name;
    public String email;
    public String cep;

    public PlayerResponse(Long id, String name, String email, String cep){
        this.id = id;
        this.name = name;
        this. email = email;
        this.cep = cep;
    }
}
