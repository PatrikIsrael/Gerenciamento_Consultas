package com.Gerenciamento.Consulta.controller;

import com.Gerenciamento.Consulta.entity.Consultation;
import com.Gerenciamento.Consulta.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @GetMapping
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        List<Consultation> consultations = consultationService.findAllConsultations();
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) {
        Consultation consultation = consultationService.findConsultationById(id);
        if (consultation != null) {
            return ResponseEntity.ok(consultation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Consultation> createConsultation(@RequestBody Consultation consultation) {
        Consultation newConsultation = consultationService.saveConsultation(consultation);
        return ResponseEntity.status(HttpStatus.CREATED).body(newConsultation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable Long id, @RequestBody Consultation consultationDetails) {
        try {
            Consultation updatedConsultation = consultationService.updateConsultation(id, consultationDetails);
            return ResponseEntity.ok(updatedConsultation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Consultation>> getConsultationsByStatus(@PathVariable String status) {
        List<Consultation> consultations = consultationService.findConsultationsByStatus(status);
        return ResponseEntity.ok(consultations);
    }
}
