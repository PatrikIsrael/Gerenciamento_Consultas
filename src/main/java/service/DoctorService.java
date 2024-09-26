package service;

import entity.Doctor;
import repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Obter todos os médicos
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    // Salvar um novo médico
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Excluir um médico por ID
    public void delete(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    // Obter um médico por ID
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.orElse(null); // Retorna null se não encontrado
    }

    // Atualizar um médico existente
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();
            existingDoctor.setName(doctorDetails.getName());
            existingDoctor.setSpecialty(doctorDetails.getSpecialty());
            existingDoctor.setUser(doctorDetails.getUser());
            return doctorRepository.save(existingDoctor);
        }
        return null; // Retorna null se não encontrado
    }

    // Salvar um médico (método adicional)
    public Doctor saveDoctor(Doctor doctor) {
        return save(doctor); // Usa o método save já existente
    }
}
