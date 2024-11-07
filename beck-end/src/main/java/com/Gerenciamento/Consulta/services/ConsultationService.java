package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.entity.Consultation;
import com.Gerenciamento.Consulta.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public List<Consultation> findAllConsultations() {
        return consultationRepository.findAll();
    }

    public Consultation findConsultationById(Long id) {
        return consultationRepository.findById(id).orElse(null);
    }

    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    public Consultation updateConsultation(Long id, Consultation consultationDetails) {
        Consultation existingConsultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        existingConsultation.setConsultationDate(consultationDetails.getConsultationDate());
        existingConsultation.setStatus(consultationDetails.getStatus());
        existingConsultation.setDoctor(consultationDetails.getDoctor());
        existingConsultation.setPatient(consultationDetails.getPatient());

        return consultationRepository.save(existingConsultation);
    }

    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    public List<Consultation> findConsultationsByStatus(String status) {
        return consultationRepository.findByStatus(status);
    }
}
