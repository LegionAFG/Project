package com.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Datum darf nicht leer sein")
    private LocalDate date;

    @NotNull(message = "Zeit darf nicht leer sein")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @NotBlank(message = "Institution darf nicht leer sein")
    @Pattern(regexp = "^\\p{L}+$", message = "Institution darf nur Buchstaben enthalten")
    private String institution;

    @NotBlank(message = "Stadt darf nicht leer sein")
    @Pattern(regexp = "^\\p{L}+$", message = "Stadt darf nur Buchstaben enthalten")
    private String city;

    @NotBlank(message = "Straße darf nicht leer sein")
    @Pattern(
            regexp = "^[\\p{L}\\d\\s/\\-.]+$",
            message = "Straße darf nur Buchstaben, Zahlen, Leerzeichen, '/', '-' und '.' enthalten")
    private String street;

    @NotNull(message = "PLZ darf nicht leer sein")
    @Min(value = 1000, message = "PLZ muss mindestens vierstellig sein")
    @Max(value = 99999, message = "PLZ darf höchstens fünfstellig sein")
    private Integer postalCode;

    @NotBlank(message = "Status darf nicht leer sein")
    private String status;
}
