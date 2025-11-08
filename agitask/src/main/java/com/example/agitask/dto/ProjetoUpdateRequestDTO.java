package com.example.agitask.dto;

import com.example.agitask.enums.Priorizacao;
import com.example.agitask.enums.Tipo;

import java.time.LocalDateTime;

public record ProjetoUpdateRequestDTO(
        String titulo,
        String descricao,
        Priorizacao priorizacao,
        Tipo tipo,
        LocalDateTime prazo,
        String emailGestor,
        String emailSupervisor
) {
}
