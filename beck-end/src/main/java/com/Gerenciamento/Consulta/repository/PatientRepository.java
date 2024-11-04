package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
        List<Patient> findByName(String Name);
        List<Patient> findBycpf (String cpf);
}
