package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
        List<Patient> findByName(String Name);
        List<Patient> findBycpf (String cpf);
}
