package com.example.project.repository;

import com.example.project.model.Histories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriesRepository extends JpaRepository<Histories, Long> {
}
