package com.Gerenciamento.Consulta.dto;

import com.Gerenciamento.Consulta.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String login;
    private String email;
    private UserRole role;
}
