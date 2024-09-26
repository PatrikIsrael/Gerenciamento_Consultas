package service;

import entity.Appointment;
import repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Obter consultas por médico dentro de um intervalo de tempo
    public List<Appointment> findByDoctor(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, start, end);
    }

    // Obter consultas por paciente
    public List<Appointment> findByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // Salvar uma nova consulta
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Excluir uma consulta
    public void delete(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    // Obter todas as consultas
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll(); // Supondo que você tenha esse método no seu repositório
    }

    // Obter uma consulta por ID
    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElse(null); // Retorna null se não encontrado, pode alterar para lançar exceção se preferir
    }

    // Atualizar uma consulta existente
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment existingAppointment = optionalAppointment.get();
            existingAppointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
            existingAppointment.setPatient(appointmentDetails.getPatient());
            existingAppointment.setDoctor(appointmentDetails.getDoctor());
            // Adicione outras propriedades que você deseja atualizar
            return appointmentRepository.save(existingAppointment);
        }
        return null; // Retorna null se não encontrado, pode alterar para lançar exceção se preferir
    }

    // Excluir uma consulta por ID
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
