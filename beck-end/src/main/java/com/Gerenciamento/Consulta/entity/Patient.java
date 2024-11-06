package com.Gerenciamento.Consulta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "patients")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private String dateOfBirth;

    @Setter
    @Column(nullable = false)
    private String cpf;

    @Setter
    @Column(nullable = false)
    private String phoneNumber;

    private String medicalHistory;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate registrationDate;
}
