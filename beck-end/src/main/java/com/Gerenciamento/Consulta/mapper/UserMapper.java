package com.Gerenciamento.Consulta.mapper;

import com.Gerenciamento.Consulta.dto.UserDTO;
import com.Gerenciamento.Consulta.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);

    List<UserDTO> toDTOList(List<User> users);
    List<User> toEntityList(List<UserDTO> userDTOs);
}
