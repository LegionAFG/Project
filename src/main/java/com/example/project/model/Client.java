package com.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^\\p{L}+$", message = "Nachname darf nur Buchstaben enthalten")
    private String lastname;
    @NotBlank
    @Pattern(regexp = "^\\p{L}+$", message = "Vorname darf nur Buchstaben enthalten")
    private String firstname;
    @Past(message = "Geburtsdatum muss in der Vergangenheit liegen")
    private LocalDate birthdate;
    @NotNull(message = "Geschlecht muss gesetzt sein")
    private String gender;
    @NotNull(message = "Nationalität muss gesetzt sein")
    private String nationality;
    @NotNull(message = "Beziehungsstatus muss gesetzt sein")
    private String relationship;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Histories> histories = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

}
