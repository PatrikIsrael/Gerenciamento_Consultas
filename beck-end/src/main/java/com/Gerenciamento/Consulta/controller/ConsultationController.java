package com.Gerenciamento.Consulta.controller;

import com.Gerenciamento.Consulta.dto.ConsultationDTO;
import com.Gerenciamento.Consulta.service.ConsultationService;
import com.Gerenciamento.Consulta.exceptions.InvalidRequestException;
import com.Gerenciamento.Consulta.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<ConsultationDTO>> getAllConsultations() {
        List<ConsultationDTO> consultations = consultationService.findAllConsultations();
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        try {
            ConsultationDTO consultation = consultationService.findConsultationById(id);
            return ResponseEntity.ok(consultation);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@Valid @RequestBody ConsultationDTO consultationDTO) {
        try {
            ConsultationDTO newConsultation = consultationService.saveConsultation(consultationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newConsultation);
        } catch (InvalidRequestException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(@PathVariable Long id, @Valid @RequestBody ConsultationDTO consultationDetails) {
        try {
            ConsultationDTO updatedConsultation = consultationService.updateConsultation(id, consultationDetails);
            return ResponseEntity.ok(updatedConsultation);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidRequestException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        try {
            consultationService.deleteConsultation(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConsultationDTO>> getConsultationsByStatus(@PathVariable String status) {
        try {
            List<ConsultationDTO> consultations = consultationService.findConsultationsByStatus(status);
            return ResponseEntity.ok(consultations);
        } catch (InvalidRequestException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
