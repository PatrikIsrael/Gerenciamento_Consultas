package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.entity.Consultation;
import com.Gerenciamento.Consulta.exceptions.InvalidRequestException;
import com.Gerenciamento.Consulta.exceptions.ResourceNotFoundException;
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
        return consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta com o ID " + id + " não encontrada."));
    }

    public Consultation saveConsultation(Consultation consultation) {
        if (consultation.getConsultationDate() == null || consultation.getDoctor() == null || consultation.getPatient() == null) {
            throw new InvalidRequestException("Dados obrigatórios da consulta estão ausentes.");
        }
        return consultationRepository.save(consultation);
    }

    public Consultation updateConsultation(Long id, Consultation consultationDetails) {
        Consultation existingConsultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta com o ID " + id + " não encontrada."));

        if (consultationDetails.getConsultationDate() == null) {
            throw new InvalidRequestException("A data da consulta é obrigatória.");
        }

        existingConsultation.setConsultationDate(consultationDetails.getConsultationDate());
        existingConsultation.setStatus(consultationDetails.getStatus());
        existingConsultation.setDoctor(consultationDetails.getDoctor());
        existingConsultation.setPatient(consultationDetails.getPatient());

        return consultationRepository.save(existingConsultation);
    }

    public void deleteConsultation(Long id) {
        if (!consultationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta com o ID " + id + " não encontrada.");
        }
        consultationRepository.deleteById(id);
    }

    public List<Consultation> findConsultationsByStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new InvalidRequestException("O status da consulta é obrigatório.");
        }
        return consultationRepository.findByStatus(status);
    }
}
