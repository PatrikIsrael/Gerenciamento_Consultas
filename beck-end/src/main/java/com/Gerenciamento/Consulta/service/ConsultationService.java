package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.dto.ConsultationDTO;
import com.Gerenciamento.Consulta.entity.Consultation;
import com.Gerenciamento.Consulta.exceptions.InvalidRequestException;
import com.Gerenciamento.Consulta.exceptions.ResourceNotFoundException;
import com.Gerenciamento.Consulta.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public List<ConsultationDTO> findAllConsultations() {
        return consultationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ConsultationDTO findConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta com o ID " + id + " não encontrada."));
        return convertToDTO(consultation);
    }

    public ConsultationDTO saveConsultation(ConsultationDTO consultationDTO) {
        if (consultationDTO.getConsultationDate() == null || consultationDTO.getDoctorId() == null || consultationDTO.getPatientId() == null) {
            throw new InvalidRequestException("Dados obrigatórios da consulta estão ausentes.");
        }

        Consultation consultation = convertToEntity(consultationDTO);
        Consultation savedConsultation = consultationRepository.save(consultation);
        return convertToDTO(savedConsultation);
    }

    public ConsultationDTO updateConsultation(Long id, ConsultationDTO consultationDTO) {
        Consultation existingConsultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta com o ID " + id + " não encontrada."));

        if (consultationDTO.getConsultationDate() == null) {
            throw new InvalidRequestException("A data da consulta é obrigatória.");
        }

        existingConsultation.setConsultationDate(consultationDTO.getConsultationDate());
        existingConsultation.setStatus(consultationDTO.getStatus());
        existingConsultation.setDoctorId(consultationDTO.getDoctorId());
        existingConsultation.setPatientId(consultationDTO.getPatientId());

        Consultation updatedConsultation = consultationRepository.save(existingConsultation);
        return convertToDTO(updatedConsultation);
    }

    public void deleteConsultation(Long id) {
        if (!consultationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta com o ID " + id + " não encontrada.");
        }
        consultationRepository.deleteById(id);
    }

    public List<ConsultationDTO> findConsultationsByStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new InvalidRequestException("O status da consulta é obrigatório.");
        }
        return consultationRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ConsultationDTO convertToDTO(Consultation consultation) {
        ConsultationDTO dto = new ConsultationDTO();
        dto.setId(consultation.getId());
        dto.setConsultationDate(consultation.getConsultationDate());
        dto.setStatus(consultation.getStatus());
        dto.setDoctorId(consultation.getDoctor().getId());
        dto.setPatientId(consultation.getPatient().getId());
        return dto;
    }

    private Consultation convertToEntity(ConsultationDTO consultationDTO) {
        Consultation consultation = new Consultation();
        consultation.setId(consultationDTO.getId());
        consultation.setConsultationDate(consultationDTO.getConsultationDate());
        consultation.setStatus(consultationDTO.getStatus());
        // Assuming you have methods to find doctor and patient by ID
        consultation.setDoctor(doctorRepository.findById(consultationDTO.getDoctorId()).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado")));
        consultation.setPatient(patientRepository.findById(consultationDTO.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado")));
        return consultation;
    }
}
