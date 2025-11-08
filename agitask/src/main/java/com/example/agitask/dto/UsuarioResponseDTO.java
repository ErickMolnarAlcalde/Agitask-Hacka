package com.example.agitask.dto;

import com.example.agitask.enums.CargoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioResponseDTO(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        CargoUsuario cargo) {
}