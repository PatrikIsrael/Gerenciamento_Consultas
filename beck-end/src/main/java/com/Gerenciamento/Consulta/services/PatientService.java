package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.entity.Patient;
import com.Gerenciamento.Consulta.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient>findAllPatient(){
        return patientRepository.findAll();
    }

    public Patient findById (Long Id){
        return patientRepository.findById(Id).orElse(null);
    }

    public Patient savePatient(Patient patient) throws IllegalAccessException {
        if(patientRepository.existsByCpf(patient.getCpf())){
            throw new IllegalAccessException("CPF já cadastrado para outro paciente.");
        }
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient){
        Patient existingPatient = patientRepository.findById(patient.getId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        existingPatient.setName(patient.getName());
        existingPatient.setCpf((patient.getCpf()));
        existingPatient.setDateOfBirth(patient.getDateOfBirth());
        existingPatient.setPhoneNumber(patient.getPhoneNumber());

        return patientRepository.save(existingPatient);
    }

    public boolean deletePatient (Long id){
      if(patientRepository.existsById(id)){
          patientRepository.deleteById(id);
          return true;
      }else{
          return false;
      }
    }

    public List<Patient> findByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }

    public Patient findByCpf(String cpf) {
        return patientRepository.findByCpf(cpf).orElse(null);
    }
    public boolean existsByCpf(String cpf) {
        return patientRepository.existsByCpf(cpf);
    }

    public long countAllPatients() {
        return patientRepository.count();
    }

    public List<Patient> findByRegistrationDate(LocalDate startDate, LocalDate endDate) {
        return patientRepository.findByRegistrationDateBetween(startDate, endDate);
    }


}
