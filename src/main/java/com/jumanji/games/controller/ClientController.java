package com.jumanji.games.controller;

import com.jumanji.games.entity.Client;
import com.jumanji.games.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping
    public ResponseEntity<Iterable<Client>> getAll(){
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){
        clientService.update(id, client);
        return ResponseEntity.ok(client);
    }
}
