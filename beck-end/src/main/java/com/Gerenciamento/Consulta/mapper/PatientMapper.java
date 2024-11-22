package com.Gerenciamento.Consulta.mapper;

import com.Gerenciamento.Consulta.dto.PatientDTO;
import com.Gerenciamento.Consulta.entity.Patient;

import java.util.List;

public interface PatientMapper {
    Patient toEntity(PatientDTO patientDTO);
    PatientDTO toDTO(Patient patient);

    List<PatientDTO> toDTOList(List<Patient> patients);
}

