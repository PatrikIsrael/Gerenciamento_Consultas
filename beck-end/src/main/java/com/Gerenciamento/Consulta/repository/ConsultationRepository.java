package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entities.Consultation;
import com.Gerenciamento.Consulta.entities.Doctor;
import com.Gerenciamento.Consulta.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByPatient(Patient patient);
    List<Consultation> findByPatient_Id(Long patientId);
    List<Consultation> findByDoctor(Doctor doctor);
    List<Consultation> findByStatus(String status);

}


