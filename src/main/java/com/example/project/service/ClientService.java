package com.example.project.service;

import com.example.project.model.Client;
import com.example.project.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public void saveClient(@Valid Client client) {

        if (client == null) {
            log.warn("Versuch, einen null-Client zu speichern");
            throw new IllegalArgumentException("Klient nicht gespeichert: keine Daten übergeben");
        }

        log.info("Klient gespeichert {}", client);
        clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Klient nicht gefunden"));
    }

    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public void deleteClientById(Long id) {
        if(!clientRepository.existsById(id))
            throw new EntityNotFoundException("Klient mit ID " + id + " nicht gefunden");
        log.info("Klient gelöscht mit ID : {}", id);
        clientRepository.deleteById(id);
    }
}
