package service;

import entity.Patient;
import repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Obter todos os pacientes
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    // Salvar um novo paciente
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    // Excluir um paciente por ID
    public void delete(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    // Obter todos os pacientes (método redundante)
    public List<Patient> getAllPatients() {
        return findAll(); // Chama o método já existente
    }

    // Obter um paciente por ID
    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null); // Retorna null se não encontrado
    }

    // Atualizar um paciente existente
    public Patient updatePatient(Long id, Patient patientDetails) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();
            existingPatient.setName(patientDetails.getName());
            existingPatient.setEmail(patientDetails.getEmail());
            existingPatient.setPhone(patientDetails.getPhone());
            existingPatient.setUser(patientDetails.getUser());
            // Adicione outras propriedades que você deseja atualizar
            return patientRepository.save(existingPatient);
        }
        return null; // Retorna null se não encontrado
    }

    // Excluir um paciente por ID
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient savePatient(Patient patient) {
        return null;
    }
}
