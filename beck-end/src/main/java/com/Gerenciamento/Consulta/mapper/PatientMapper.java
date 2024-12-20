package com.Gerenciamento.Consulta.mapper;

import com.Gerenciamento.Consulta.dto.PatientDTO;
import com.Gerenciamento.Consulta.entities.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toEntity(PatientDTO patientDTO);
    PatientDTO toDTO(Patient patient);
    List<PatientDTO> toDTOList(List<Patient> patients);
}

