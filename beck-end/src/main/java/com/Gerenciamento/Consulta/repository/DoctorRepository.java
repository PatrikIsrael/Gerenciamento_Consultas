package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository {
        List<Doctor> findBySpecialty(String specialty);
}
