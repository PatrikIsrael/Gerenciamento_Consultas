package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
        List<Patient> findByName(String Name);
        List<Patient> findBycpf (String cpf);
        boolean existsByCpf(String cpf);
        Optional<Patient> findByCpf(String cpf);
        List<Patient> findByNameContainingIgnoreCase(String name);
        List<Patient> findByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);
}
