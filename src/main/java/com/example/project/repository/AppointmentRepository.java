package com.example.project.repository;

import com.example.project.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT h FROM Appointment h JOIN FETCH h.client c WHERE c.id = :clientId")
    List<Appointment> findByClientId(Long clientId);

    @Query("SELECT a FROM Appointment a JOIN FETCH a.client WHERE a.status = :status")
    List<Appointment> findAllByStatusWithClient(String status);



}
