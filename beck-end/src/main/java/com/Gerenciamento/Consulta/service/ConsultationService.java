package com.Gerenciamento.Consulta.service;

import com.Gerenciamento.Consulta.dto.ConsultationDTO;
import com.Gerenciamento.Consulta.entities.Consultation;
import com.Gerenciamento.Consulta.entities.Doctor;
import com.Gerenciamento.Consulta.entities.Patient;
import com.Gerenciamento.Consulta.exceptions.InvalidRequestException;
import com.Gerenciamento.Consulta.exceptions.ResourceNotFoundException;
import com.Gerenciamento.Consulta.mapper.ConsultationMapper;
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

    @Autowired
    private ConsultationMapper consultationMapper;

    public List<ConsultationDTO> findAllConsultations() {
        List<Consultation> consultations = consultationRepository.findAll();
        return consultationMapper.toDTOList(consultations);
    }

    public ConsultationDTO findConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta com o ID " + id + " não encontrada."));
        return consultationMapper.toDTO(consultation);
    }

    public ConsultationDTO saveConsultation(ConsultationDTO consultationDTO) {
        validateConsultationDTO(consultationDTO);

        Doctor doctor = findDoctorById(consultationDTO.getDoctorId());
        Patient patient = findPatientById(consultationDTO.getPatientId());

        Consultation consultation = consultationMapper.toEntity(consultationDTO);
        consultation.setDoctor(doctor);
        consultation.setPatient(patient);

        Consultation savedConsultation = consultationRepository.save(consultation);
        return consultationMapper.toDTO(savedConsultation);
    }

    public ConsultationDTO updateConsultation(Long id, ConsultationDTO consultationDTO) {
        validateConsultationDTO(consultationDTO);

        Consultation existingConsultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta com o ID " + id + " não encontrada."));

        existingConsultation.setConsultationDate(consultationDTO.getConsultationDate());
        existingConsultation.setStatus(consultationDTO.getStatus());
        existingConsultation.setDoctor(findDoctorById(consultationDTO.getDoctorId()));
        existingConsultation.setPatient(findPatientById(consultationDTO.getPatientId()));

        Consultation updatedConsultation = consultationRepository.save(existingConsultation);
        return consultationMapper.toDTO(updatedConsultation);
    }

    public void deleteConsultation(Long id) {
        if (!consultationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta com o ID " + id + " não encontrada.");
        }
        consultationRepository.deleteById(id);
    }

    public List<ConsultationDTO> findConsultationsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidRequestException("O status da consulta é obrigatório.");
        }
        return consultationRepository.findByStatus(status).stream()
                .map(consultationMapper::toDTO)
                .collect(Collectors.toList());
    }


    private Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com o ID " + id + " não encontrado."));
    }

    private Patient findPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente com o ID " + id + " não encontrado."));
    }

    private void validateConsultationDTO(ConsultationDTO consultationDTO) {
        if (consultationDTO.getConsultationDate() == null ||
                consultationDTO.getDoctorId() == null ||
                consultationDTO.getPatientId() == null) {
            throw new InvalidRequestException("Dados obrigatórios da consulta estão ausentes.");
        }
    }
}
