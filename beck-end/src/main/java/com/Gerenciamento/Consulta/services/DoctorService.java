package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.entity.Doctor;
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
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor updateDoctor (Long id, Doctor doctor){
        Doctor existingDoctor = doctorRepository.findById(doctor.getId())
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));

        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialty(doctor.getSpecialty());

        return doctorRepository.save(existingDoctor);
    }
    public Doctor saveDoctor (Doctor doctor) {
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
