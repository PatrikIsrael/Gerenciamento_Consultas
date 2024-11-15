package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.entity.Doctor;
import com.Gerenciamento.Consulta.exceptions.InvalidRequestException;
import com.Gerenciamento.Consulta.exceptions.ResourceNotFoundException;
import com.Gerenciamento.Consulta.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findById(Long id){
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com o ID " + id + " não encontrado."));
    }

    public Doctor updateDoctor(Long id, Doctor doctorDetails){
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com o ID " + id + " não encontrado."));

        if (doctorDetails.getName() == null || doctorDetails.getSpecialty() == null) {
            throw new InvalidRequestException("Nome e especialidade são obrigatórios para atualização.");
        }

        existingDoctor.setName(doctorDetails.getName());
        existingDoctor.setSpecialty(doctorDetails.getSpecialty());

        return doctorRepository.save(existingDoctor);
    }
    public Doctor saveDoctor(Doctor doctor) {
        if (doctor.getName() == null || doctor.getSpecialty() == null) {
            throw new InvalidRequestException("Nome e especialidade são obrigatórios para salvar um médico.");
        }
        return doctorRepository.save(doctor);
    }

    public boolean deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Doctor>findBySpeciality(String specialty){
        return doctorRepository.findBySpecialty(specialty);
    }

}
