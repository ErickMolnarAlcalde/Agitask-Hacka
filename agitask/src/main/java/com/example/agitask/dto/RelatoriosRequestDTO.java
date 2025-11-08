package com.example.agitask.dto;

import com.example.agitask.model.Projeto;
import com.example.agitask.model.Usuario;
import jakarta.validation.constraints.NotBlank;

public record RelatoriosRequestDTO(

        @NotBlank
        Projeto idProjeto,

        @NotBlank
        Long idTarefa,

        @NotBlank
        Usuario idResponsavel) {
}
