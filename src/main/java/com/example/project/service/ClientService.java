package com.example.project.service;

import com.example.project.model.Client;
import com.example.project.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public void saveClient(Client client) {
        log.info("Klient gespeichert {}", client);
        clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Klient nicht gefunden"));
    }

    @Transactional
    public void deleteClientById(Long id) {
        if(!clientRepository.existsById(id))
            throw new EntityNotFoundException("Klient mit ID " + id + " nicht gefunden");
        log.info("Klient gelöscht mit ID : {}", id);
        clientRepository.deleteById(id);
    }
}
