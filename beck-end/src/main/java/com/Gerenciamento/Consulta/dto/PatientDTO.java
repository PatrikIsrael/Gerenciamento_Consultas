package com.Gerenciamento.Consulta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private long id;
    private String name;
    private String cpf;
    private String dateOfBirth;
    private String phoneNumber;
    private String medicalHistory;
}
