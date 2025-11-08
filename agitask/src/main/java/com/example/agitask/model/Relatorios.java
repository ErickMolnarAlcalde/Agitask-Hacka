package com.example.agitask.model;

import com.example.agitask.dto.RelatoriosRequestDTO;
import com.example.agitask.dto.RelatoriosResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Relatorios")
@Entity(name = "Relatorios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Relatorios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto idProjeto;

    private Long idTarefa;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario idResponsavel;

    public Relatorios(RelatoriosRequestDTO dados) {
        this.idProjeto = dados.idProjeto();
        this.idTarefa = dados.idTarefa();
        this.idResponsavel = dados.idResponsavel();
    }

    public Relatorios(RelatoriosResponseDTO dados) {
        this.idProjeto = dados.idProjeto();
        this.idTarefa = dados.idTarefa();
        this.idResponsavel = dados.idResponsavel();
    }


}
