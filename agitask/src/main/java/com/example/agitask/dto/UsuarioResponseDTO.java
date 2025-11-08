package com.example.agitask.dto;

import com.example.agitask.enums.CargoUsuario;
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