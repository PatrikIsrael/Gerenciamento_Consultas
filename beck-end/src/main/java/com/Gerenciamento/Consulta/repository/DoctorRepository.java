package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository <Doctor, Long> {
        List<Doctor> findBySpecialty(String specialty);
}
