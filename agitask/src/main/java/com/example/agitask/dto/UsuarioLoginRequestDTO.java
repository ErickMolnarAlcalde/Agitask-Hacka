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
public class UsuarioLoginRequestDTO {

    @NotBlank
    private String senha;
    @NotBlank

    @Email
    private String email;

}