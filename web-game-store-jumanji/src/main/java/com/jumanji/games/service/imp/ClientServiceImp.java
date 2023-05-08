package com.jumanji.games.service.imp;

import com.jumanji.games.entity.Address;
import com.jumanji.games.entity.Client;
import com.jumanji.games.repository.IAddressRepository;
import com.jumanji.games.repository.IClientRepository;
import com.jumanji.games.service.IClientService;
import com.jumanji.games.service.IViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements IClientService {
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IAddressRepository addressRepository;
    @Autowired
    private IViaCepService viaCepService;

    @Override
    public Iterable<Client> getAll() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    @Override
    public Client getById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    @Override
    public void insert(Client client) {
        setClientAddress(client);
        clientRepository.save(client);

    }

    @Override
    public void update(Long id, Client client) {
        Optional<Client> clientF = clientRepository.findById(id);
        if(clientF.isPresent()){
            setClientAddress(client);
            clientRepository.save(client);

        }
    }

    @Override
    public void delete(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        client.ifPresent(value -> clientRepository.delete(value));
    }

    @Override
    public void setClientAddress(Client client) {
        try {
            String zipCode = client.getAddress().getCep();
            Address address = addressRepository.findById(zipCode).orElseGet(() -> {
                Address newAddress = viaCepService.getZipCode(zipCode);
                addressRepository.save(newAddress);
                return newAddress;
            });
            client.setAddress(address);

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.stream(e.getStackTrace()));
        }
    }
}
