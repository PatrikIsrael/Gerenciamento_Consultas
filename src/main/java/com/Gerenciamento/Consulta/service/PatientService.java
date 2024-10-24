package com.Gerenciamento.Consulta.controller.service;

import com.Gerenciamento.Consulta.controller.entity.Patient;
import com.Gerenciamento.Consulta.controller.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;


    public List<Patient> findAll() {
        return patientRepository.findAll();
    }


    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }


    public void delete(Long patientId) {
        patientRepository.deleteById(patientId);
    }




    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null);
    }


    public Patient updatePatient(Long id, Patient patientDetails) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();
            existingPatient.setName(patientDetails.getName());
            existingPatient.setEmail(patientDetails.getEmail());
            existingPatient.setPhone(patientDetails.getPhone());
            existingPatient.setUser(patientDetails.getUser());

            return patientRepository.save(existingPatient);
        }
        return null;
    }

    public Optional<Patient> findByUserName(String username){
        return patientRepository.findByUserName(username);
    }

    public Patient savePatient(Patient patient) {
        return null;
    }
}
