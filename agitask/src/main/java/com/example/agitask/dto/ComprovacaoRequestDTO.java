package com.example.agitask.dto;

import java.util.UUID;

public record ComprovacaoRequestDTO(
        String descricao,
        UUID projetoId,
        String emailAutor
) {}

