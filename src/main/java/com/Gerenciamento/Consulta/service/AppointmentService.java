package com.Gerenciamento.Consulta.controller.service;

import com.Gerenciamento.Consulta.controller.entity.Appointment;
import com.Gerenciamento.Consulta.controller.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;


    public List<Appointment> findByDoctor(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, start, end);
    }


    public List<Appointment> findByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }


    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


    public void delete(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }


    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElse(null);
    }


    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment existingAppointment = optionalAppointment.get();
            existingAppointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
            existingAppointment.setPatient(appointmentDetails.getPatient());
            existingAppointment.setDoctor(appointmentDetails.getDoctor());
            return appointmentRepository.save(existingAppointment);
        }
        return null;
    }


    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
