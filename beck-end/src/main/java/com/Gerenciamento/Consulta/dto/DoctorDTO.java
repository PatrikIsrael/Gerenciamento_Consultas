package com.Gerenciamento.Consulta.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long id;
    private String name;
    private String specialty;
    private Long userId;
}
