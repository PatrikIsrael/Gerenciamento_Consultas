package com.Gerenciamento.Consulta.controller.repository;

import com.Gerenciamento.Consulta.controller.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
