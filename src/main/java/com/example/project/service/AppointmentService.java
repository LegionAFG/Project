package com.example.project.service;

import com.example.project.model.Appointment;
import com.example.project.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Transactional
    public void saveAppointment(Appointment appointment) {
        log.info("Termin gespeichert {}", appointment);
        appointmentRepository.save(appointment);
    }

    @Transactional(readOnly = true)
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Termin mit nicht gefunden"));
    }

    @Transactional
    public void deleteAppointment(Long id){
        if(!appointmentRepository.existsById(id))
            throw new EntityNotFoundException("Termin mit ID " + id + " nicht gefunden");
        log.info("Appointment gelöscht mit ID : {}", id);
        appointmentRepository.deleteById(id);
    }
}
