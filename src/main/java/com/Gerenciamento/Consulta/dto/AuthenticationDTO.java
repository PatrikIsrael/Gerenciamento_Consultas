package com.Gerenciamento.Consulta.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank String login,
        @NotBlank String password
) {
}
