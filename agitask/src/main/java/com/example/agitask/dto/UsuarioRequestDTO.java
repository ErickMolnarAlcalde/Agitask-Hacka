package com.example.agitask.dto;

import com.example.agitask.enums.CargoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(

        @NotBlank
        @Email
        String emailCargo,

        @NotBlank
        String nome,

        @NotBlank
        String senha,

        @NotBlank
        @Email
        String email,

        @NotBlank
        CargoUsuario cargo) {
}