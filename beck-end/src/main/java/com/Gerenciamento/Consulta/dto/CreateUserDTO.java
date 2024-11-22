package com.Gerenciamento.Consulta.dto;

import com.Gerenciamento.Consulta.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    private String login;
    private String password;
    private String email;
    private UserRole role;

}
