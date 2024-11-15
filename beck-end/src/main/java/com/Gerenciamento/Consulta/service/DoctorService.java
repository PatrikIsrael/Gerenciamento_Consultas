package com.Gerenciamento.Consulta.service;

import com.Gerenciamento.Consulta.dto.DoctorDTO;
import com.Gerenciamento.Consulta.entity.Doctor;
import com.Gerenciamento.Consulta.entity.User;
import com.Gerenciamento.Consulta.exceptions.InvalidRequestException;
import com.Gerenciamento.Consulta.exceptions.ResourceNotFoundException;
import com.Gerenciamento.Consulta.repository.DoctorRepository;
import com.Gerenciamento.Consulta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    public List<DoctorDTO> findAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO findById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com o ID " + id + " não encontrado."));
        return convertToDTO(doctor);
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com o ID " + id + " não encontrado."));

        if (doctorDTO.getName() == null || doctorDTO.getSpecialty() == null) {
            throw new InvalidRequestException("Nome e especialidade são obrigatórios para atualização.");
        }

        existingDoctor.setName(doctorDTO.getName());
        existingDoctor.setSpecialty(doctorDTO.getSpecialty());

        if (doctorDTO.getUserId() != null) {
            User user = userRepository.findById(doctorDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID " + doctorDTO.getUserId() + " não encontrado."));
            existingDoctor.setUser(user);
        }

        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return convertToDTO(updatedDoctor);
    }

    public Doctor saveDoctor(DoctorDTO doctorDTO) {
        if (doctorDTO.getName() == null || doctorDTO.getSpecialty() == null) {
            throw new InvalidRequestException("Nome e especialidade são obrigatórios para salvar um médico.");
        }

        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setSpecialty(doctorDTO.getSpecialty());

        if (doctorDTO.getUserId() != null) {
            User user = userRepository.findById(doctorDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID " + doctorDTO.getUserId() + " não encontrado."));
            doctor.setUser(user);
        }

        Doctor savedDoctor = doctorRepository.save(doctor);
        return (Doctor) convertToDTO(savedDoctor);
    }

    public boolean deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Médico com o ID " + id + " não encontrado.");
        }
    }

    public List<DoctorDTO> findBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DoctorDTO convertToDTO(Doctor doctor) {
        Long userId = doctor.getUser() != null ? doctor.getUser().getId() : null;
        return new DoctorDTO(doctor.getId(), doctor.getName(), doctor.getSpecialty(), userId);
    }
}
