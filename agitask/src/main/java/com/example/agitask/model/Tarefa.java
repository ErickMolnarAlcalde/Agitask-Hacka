package com.example.agitask.model;

import com.example.agitask.enums.Priorizacao;
import com.example.agitask.enums.Status;
import com.example.agitask.model.Usuario;
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
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priorizacao priorizacao;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private LocalDateTime dataCriacao;

    private LocalDateTime prazo;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "gestor_id")
    private Usuario gestor;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Usuario surpevisor;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Usuario colaborador;

    @ManyToOne
    @JoinColumn(name = "tarefa_pai_id")
    private Tarefa tarefaPai;

    @OneToMany(mappedBy = "tarefaPai", cascade = CascadeType.ALL)
    private List<Tarefa> subtarefas;

}
