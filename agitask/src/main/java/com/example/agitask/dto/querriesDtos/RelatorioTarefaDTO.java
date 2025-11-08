package com.example.agitask.dto.querriesDtos;

import com.example.agitask.enums.Priorizacao;
import com.example.agitask.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioTarefaDTO {

    private String titulo;
    private String colaborador;
    private String supervisor;
    private String gestor;
    private Status status;          // trocado para enum
    private Priorizacao priorizacao; // trocado para enum
    private LocalDateTime prazo;
    private LocalDateTime dataCriacao;

}
