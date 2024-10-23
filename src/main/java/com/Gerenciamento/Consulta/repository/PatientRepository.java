package com.Gerenciamento.Consulta.controller.repository;


import com.Gerenciamento.Consulta.controller.entity.Patient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient>findByUserName(String name);
}
