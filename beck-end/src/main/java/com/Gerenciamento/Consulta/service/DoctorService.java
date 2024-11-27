package com.Gerenciamento.Consulta.service;

import com.Gerenciamento.Consulta.dto.DoctorDTO;
import com.Gerenciamento.Consulta.entities.Doctor;
import com.Gerenciamento.Consulta.entities.User;
import com.Gerenciamento.Consulta.exceptions.InvalidRequestException;
import com.Gerenciamento.Consulta.exceptions.ResourceNotFoundException;
import com.Gerenciamento.Consulta.mapper.DoctorMapper;
import com.Gerenciamento.Consulta.repository.DoctorRepository;
import com.Gerenciamento.Consulta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    public List<DoctorDTO> findAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorMapper.toDTOList(doctors);
    }

    public DoctorDTO findById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com o ID " + id + " não encontrado."));
        return doctorMapper.toDTO(doctor);
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
        return doctorMapper.toDTO(updatedDoctor);
    }

    public DoctorDTO saveDoctor(DoctorDTO doctorDTO) {
        if (doctorDTO.getName() == null || doctorDTO.getSpecialty() == null) {
            throw new InvalidRequestException("Nome e especialidade são obrigatórios para salvar um médico.");
        }

        Doctor doctor = doctorMapper.toEntity(doctorDTO);

        if (doctorDTO.getUserId() != null) {
            User user = userRepository.findById(doctorDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID " + doctorDTO.getUserId() + " não encontrado."));
            doctor.setUser(user);
        }

        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(savedDoctor);
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
        List<Doctor> doctors = doctorRepository.findBySpecialty(specialty);
        return doctorMapper.toDTOList(doctors);
    }
}
