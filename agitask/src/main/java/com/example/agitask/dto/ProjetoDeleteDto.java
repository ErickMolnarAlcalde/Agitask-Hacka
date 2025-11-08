package com.example.agitask.dto;

import java.util.UUID;

public record ProjetoDeleteDto (
        UUID id,
        String email,
        String senha
){
}
