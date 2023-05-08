package com.jumanji.games.service;

import com.jumanji.games.entity.Client;

public interface IClientService {
    Iterable<Client> getAll();
    Client getById(Long id);
    void insert(Client client);
    void update(Long id, Client client);
    void delete(Long id);
    void setClientAddress(Client client);
}
