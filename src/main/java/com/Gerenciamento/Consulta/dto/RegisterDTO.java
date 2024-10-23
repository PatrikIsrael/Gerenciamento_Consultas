package com.Gerenciamento.Consulta.dto;

import com.Gerenciamento.Consulta.entity.UserRole;

public record RegisterDTO(String login, String password, UserRole role){
}
