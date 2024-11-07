package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.entity.Doctor;
import com.Gerenciamento.Consulta.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServices {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }
    public Optional<Doctor>findDoctorById (Long Id){
        return doctorRepository.findById(Id);
    }
    public Doctor saveDoctor (Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDcotor(Long Id){
        doctorRepository.deleteById(Id);
    }
    public List<Doctor>findBySpeciality(String specialty){
        return doctorRepository.findBySpecialty(specialty);
    }

}
