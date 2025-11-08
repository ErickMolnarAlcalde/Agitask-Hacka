package com.example.agitask.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

        private UUID id;
        private String nome;
        private String email;
        private Boolean ativo;
        private Boolean ferias;
        private String cargo;
}