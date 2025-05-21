package com.example.project.service;

import com.example.project.model.Histories;
import com.example.project.repository.HistoriesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoriesService {

    private final HistoriesRepository historiesRepository;

    @Transactional
    public void saveHistories(Histories histories) {
        log.info("Historie gespeichert {}", histories);
        historiesRepository.save(histories);
    }

    @Transactional(readOnly = true)
    public Histories getHistoriesById(Long id) {
        return historiesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Historie mit nicht gefunden"));
    }

    @Transactional
    public void deleteHistorie(Long id){
        if(!historiesRepository.existsById(id))
            throw new IllegalArgumentException("Historie mit ID " + id + " nicht gefunden");
        log.info("Historie gelöscht mit ID : {}", id);
        historiesRepository.deleteById(id);
    }
}
