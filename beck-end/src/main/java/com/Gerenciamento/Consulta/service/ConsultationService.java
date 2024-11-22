package com.Gerenciamento.Consulta.service;

import com.Gerenciamento.Consulta.dto.ConsultationDTO;
import com.Gerenciamento.Consulta.entities.Consultation;
import com.Gerenciamento.Consulta.entities.Doctor;
import com.Gerenciamento.Consulta.entities.Patient;
import com.Gerenciamento.Consulta.exceptions.InvalidRequestException;
import com.Gerenciamento.Consulta.exceptions.ResourceNotFoundException;
import com.Gerenciamento.Consulta.repository.ConsultationRepository;
import com.Gerenciamento.Consulta.repository.DoctorRepository;
import com.Gerenciamento.Consulta.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;


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

        Doctor doctor = doctorRepository.findById(consultationDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        Patient patient = patientRepository.findById(consultationDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));


        Consultation consultation = new Consultation();
        consultation.setConsultationDate(consultationDTO.getConsultationDate());
        consultation.setStatus(consultationDTO.getStatus());
        consultation.setDoctor(doctor);
        consultation.setPatient(patient);

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
        existingConsultation.setDoctor(doctorRepository.findById(consultationDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado")));
        existingConsultation.setPatient(patientRepository.findById(consultationDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado")));

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

}
