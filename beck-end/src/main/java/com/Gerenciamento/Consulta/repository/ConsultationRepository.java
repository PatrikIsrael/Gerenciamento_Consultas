package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entity.Consultation;
import com.Gerenciamento.Consulta.entity.Doctor;
import com.Gerenciamento.Consulta.entity.Patient;
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


