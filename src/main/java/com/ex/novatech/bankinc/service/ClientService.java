package com.ex.novatech.bankinc.service;

import com.ex.novatech.bankinc.model.Client;
import com.ex.novatech.bankinc.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(
            final ClientRepository clientRepository
    ){
        this.clientRepository = clientRepository;
    }

    public Client save(Client client){
        return this.clientRepository.saveAndFlush(client);
    }
}
