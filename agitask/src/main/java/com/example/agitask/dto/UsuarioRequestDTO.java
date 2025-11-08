package com.example.agitask.dto;

import com.example.agitask.enums.CargoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {

        @NotBlank
        private String nome;
        @NotBlank
        private String senha;
        @NotBlank
        @Email
        private String email;
        @NotNull
        private Boolean ativo;
        @NotNull
        private Boolean ferias;
        @NotNull
        private CargoUsuario cargo;
}