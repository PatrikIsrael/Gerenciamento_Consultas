package com.Gerenciamento.Consulta.mapper;

import com.Gerenciamento.Consulta.dto.ConsultationDTO;
import com.Gerenciamento.Consulta.entities.Consultation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface ConsultationMapper {
    Consultation toEntity (ConsultationDTO consultationDTO);
    ConsultationDTO toDTO (Consultation consultation);
    List<ConsultationDTO> toDTOList (List<Consultation> consultation);

}
