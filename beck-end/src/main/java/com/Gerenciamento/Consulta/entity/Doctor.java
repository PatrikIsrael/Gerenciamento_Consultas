package com.Gerenciamento.Consulta.entity;

import com.Gerenciamento.Consulta.dto.DoctorDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor extends DoctorDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialty;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
