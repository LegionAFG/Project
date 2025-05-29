package com.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Datum darf nicht leer sein")
    private LocalDate date;
    @NotBlank(message = "Zeit darf nicht leer sein")
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime time;
    @NotBlank(message = "Institution darf nicht leer sein")
    @Pattern(regexp = "^\\p{L}+$", message = "Institution darf nur Buchstaben enthalten")
    private String institution;
    @NotBlank(message = "Stadt darf nicht leer sein")
    @Pattern(regexp = "^\\p{L}+$", message = "City darf nur Buchstaben enthalten")
    private String city;
    @NotBlank(message = "Straße darf nicht leer sein")
    @Pattern(
            regexp = "^[\\p{L}\\d\\s/\\-.]+$",
            message = "Straße darf nur Buchstaben, Zahlen, Leerzeichen, " +
                    "Schrägstrich '/', Bindestrich '-' und Punkt '.' enthalten"
    )
    private String street;
    @NotBlank(message = "PLZ darf nicht leer sein")
    @Pattern(regexp = "^\\d+$", message = "Nur Ziffern erlaubt")
    private int postalCode;
    @NotBlank(message = "Status darf nicht leer sein")
    private String status;


}
