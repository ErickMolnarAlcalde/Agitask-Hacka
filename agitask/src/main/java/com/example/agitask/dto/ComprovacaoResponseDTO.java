package com.example.agitask.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ComprovacaoResponseDTO(
        UUID id,
        String descricao,
        LocalDateTime dataCriacao,
        String autorNome
) {}