package com.example.project.repository;

import com.example.project.model.Histories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriesRepository extends JpaRepository<Histories, Long> {
    @Query("SELECT h FROM Histories h JOIN FETCH h.client c WHERE c.id = :clientId")
    List<Histories> findByClientId(Long clientId);

}
