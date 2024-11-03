package com.Gerenciamento.Consulta.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient; // Referência ao paciente

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor; // Referência ao médico

    @Column(nullable = false)
    private LocalDateTime appointmentDate; // Data e hora da consulta

    private String status; // Status da consulta (agendada, cancelada, concluída, etc.)
}
