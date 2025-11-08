package com.example.agitask.dto;

import com.example.agitask.enums.Priorizacao;
import com.example.agitask.enums.Status;
import com.example.agitask.enums.Tipo;

import java.time.LocalDateTime;

public record ProjetoResponseDTO(
        String titulo,
        String descricao,
        Status status,
        Priorizacao priorizacao,
        Tipo tipo,
        LocalDateTime dataCriacao,
        LocalDateTime prazo
) {
}
