package com.example.project.service;

import com.example.project.model.Appointment;
import com.example.project.model.Client;
import com.example.project.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Transactional
    public Appointment saveAppointment(@Valid Appointment appointment) {

        Appointment saved = appointmentRepository.save(appointment);

        log.info("Termin gespeichert (id={}, clientId={})",
                saved.getId(),
                saved.getClient() != null ? saved.getClient().getId() : null);

        return saved;
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAllOpenAppointments() {
        return appointmentRepository.findAllByStatusWithClient("Offen");
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAppointmentsByClient(Client client) {
        return appointmentRepository.findByClientId(client.getId());
    }

    @Transactional
    public void deleteAppointment(Long id){
        if(!appointmentRepository.existsById(id))
            throw new EntityNotFoundException("Termin mit ID " + id + " nicht gefunden");
        log.info("Appointment gelöscht mit ID : {}", id);
        appointmentRepository.deleteById(id);
    }
}
