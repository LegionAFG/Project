package com.example.project.service;

import com.example.project.model.Client;
import com.example.project.model.Histories;
import com.example.project.repository.HistoriesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoriesService {

    private final HistoriesRepository historiesRepository;

    @Transactional
    public Histories saveHistories(Histories histories) {

        Histories saved = historiesRepository.save(histories);

        log.info("Histories gespeichert (id={}, clientId={})",
                saved.getId(),
                saved.getClient() != null ? saved.getClient().getId() : null);

        return saved;
    }

    @Transactional(readOnly = true)
    public List<Histories> getHistoriesByClient(Client client) {
        return historiesRepository.findByClientId(client.getId());
    }

    @Transactional
    public void deleteHistories(Long id) {
        if (!historiesRepository.existsById(id))
            throw new EntityNotFoundException("Historie mit ID " + id + " nicht gefunden");
        log.info("Historie gelöscht mit ID : {}", id);
        historiesRepository.deleteById(id);
    }
}
