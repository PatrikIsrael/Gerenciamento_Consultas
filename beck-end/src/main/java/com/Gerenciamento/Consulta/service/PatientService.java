package com.Gerenciamento.Consulta.service;

import com.Gerenciamento.Consulta.dto.PatientDTO;
import com.Gerenciamento.Consulta.entity.Patient;
import com.Gerenciamento.Consulta.exceptions.InvalidRequestException;
import com.Gerenciamento.Consulta.exceptions.ResourceNotFoundException;
import com.Gerenciamento.Consulta.repository.PatientRepository;
import com.Gerenciamento.Consulta.mapper.PatientMapper;  // Importando o mapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientDTO> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toDTOList(patients);
    }

    public PatientDTO findPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente com o ID " + id + " não encontrado."));
        return patientMapper.toDTO(patient);
    }

    public PatientDTO savePatient(PatientDTO patientDTO) {

        Patient patient = patientMapper.toEntity(patientDTO);

        if (patientRepository.existsByCpf(patient.getCpf())) {
            throw new InvalidRequestException("O CPF " + patient.getCpf() + " já está cadastrado.");
        }

        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente com o ID " + id + " não encontrado."));

        if (!existingPatient.getCpf().equals(patientDTO.getCpf()) &&
                patientRepository.existsByCpf(patientDTO.getCpf())) {
            throw new InvalidRequestException("O CPF " + patientDTO.getCpf() + " já está cadastrado.");
        }


        existingPatient.setName(patientDTO.getName());
        existingPatient.setCpf(patientDTO.getCpf());
        existingPatient.setDateOfBirth(LocalDate.parse(patientDTO.getDateOfBirth()));
        existingPatient.setPhoneNumber(patientDTO.getPhoneNumber());
        existingPatient.setMedicalHistory(patientDTO.getMedicalHistory());


        Patient updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDTO(updatedPatient);
    }

    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<PatientDTO> findByName(String name) {
        List<Patient> patients = patientRepository.findByNameContainingIgnoreCase(name);
        return patientMapper.toDTOList(patients);
    }

    public PatientDTO findByCpf(String cpf) {
        Patient patient = patientRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente com o CPF " + cpf + " não encontrado."));
        return patientMapper.toDTO(patient);
    }

    public long countAllPatients() {
        return patientRepository.count();
    }

    public List<PatientDTO> findByRegistrationDate(LocalDate startDate, LocalDate endDate) {
        List<Patient> patients = patientRepository.findByRegistrationDateBetween(startDate, endDate);
        return patientMapper.toDTOList(patients);
    }
}
