package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.model.Appointment;
import com.Gerenciamento.Consulta.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> findAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Exemplo de método para obter consultas por status
    public List<Appointment> findAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }
}
