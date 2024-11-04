package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entity.Consultation;
import com.Gerenciamento.Consulta.entity.Doctor;
import org.hibernate.internal.util.collections.LazyIndexedMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository {
    List<Consultation> findByPacientId (Long PatientId);
    List<Consultation> findByDoctorId (Long DoctorId);

}
