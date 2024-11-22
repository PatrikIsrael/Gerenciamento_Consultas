package com.Gerenciamento.Consulta.dto;

import com.Gerenciamento.Consulta.entities.Consultation.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime consultationDate;
    private Status status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
