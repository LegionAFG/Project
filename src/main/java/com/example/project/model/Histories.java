package com.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "histories")
public class Histories {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title darf nicht leer sein")
    @Pattern(regexp = "^[\\p{L} ]+$",
            message = "Title darf nur Buchstaben und Leerzeichen enthalten")
    private String title;

    @NotBlank(message = "Description darf nicht leer sein")
    private String description;

    @NotNull(message = "Zeit darf nicht leer sein")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @NotNull(message = "Datum darf nicht leer sein")
    private LocalDate date;
}
