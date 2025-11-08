package com.example.agitask.model;

import com.example.agitask.enums.Priorizacao;
import com.example.agitask.enums.Status;
import com.example.agitask.enums.Tipo;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_tarefa")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;

    private String descricao;

    //PENDENTE, ANDAMENTO, CONCLUIDO,ATRASADO
    @Enumerated(EnumType.STRING)
    private Status status;

    //BAIXA, MEDIA, ALTA, URGENTE
    @Enumerated(EnumType.STRING)
    private Priorizacao priorizacao;

    //PROJETO, TAREFA
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private LocalDateTime dataCriacao;

    private LocalDateTime prazo;

    @ManyToOne
    @JoinColumn(name = "gestor_id")
    private Usuario gestor;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Usuario supervisor;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Usuario colaborador;

    @ManyToOne
    @JoinColumn(name = "projeto_pai_id")
    private Projeto projetoPai;

    @OneToMany(mappedBy = "projetoPai", cascade = CascadeType.ALL)
    private List<Projeto> tarefas;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Comprovacao> comprovacoes;

}
