package com.Gerenciamento.Consulta.controller.service;

import com.Gerenciamento.Consulta.controller.entity.Doctor;
import com.Gerenciamento.Consulta.controller.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }


    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    public void delete(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }


    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.orElse(null);
    }


    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();
            existingDoctor.setName(doctorDetails.getName());
            existingDoctor.setSpecialty(doctorDetails.getSpecialty());
            existingDoctor.setUser(doctorDetails.getUser());
            return doctorRepository.save(existingDoctor);
        }
        return null;
    }


    public Doctor saveDoctor(Doctor doctor) {
        return save(doctor);
    }
}
