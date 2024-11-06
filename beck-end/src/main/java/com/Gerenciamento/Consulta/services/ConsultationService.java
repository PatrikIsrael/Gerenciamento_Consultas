package com.Gerenciamento.Consulta.services;


import com.Gerenciamento.Consulta.entity.Consultation;
import com.Gerenciamento.Consulta.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public List<Consultation> findAllAppointments() {
        return consultationRepository.findAll();
    }

    public Optional<Consultation> findAppointmentById(Long id) {
        return consultationRepository.findById(id);
    }

    public Consultation saveAppointment(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    public void deleteAppointment(Long id) {
        consultationRepository.deleteById(id);
    }

    public List<Consultation> findAppointmentsByStatus(String status) {
        return consultationRepository.findByStatus(status);
    }
}
