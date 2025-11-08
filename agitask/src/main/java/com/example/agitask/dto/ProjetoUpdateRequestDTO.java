package com.example.agitask.dto;

import com.example.agitask.enums.Priorizacao;
import com.example.agitask.enums.Status;
import com.example.agitask.enums.Tipo;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjetoUpdateRequestDTO(
        UUID id,
        String titulo,
        String descricao,
        Status status,
        Priorizacao priorizacao,
        Tipo tipo,
        LocalDateTime prazo,
        String emailGestor,
        String emailSupervisor,
        String email,
        String senha

) {
}
