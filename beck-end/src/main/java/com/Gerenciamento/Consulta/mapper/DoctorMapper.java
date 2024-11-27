package com.Gerenciamento.Consulta.mapper;

import com.Gerenciamento.Consulta.dto.DoctorDTO;
import com.Gerenciamento.Consulta.entities.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    Doctor toEntity(DoctorDTO doctorDTO);
    DoctorDTO toDTO(Doctor doctor);
    List<DoctorDTO> toDTOList(List<Doctor> doctor);
}
