package com.example.agitask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_comparacao")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comprovacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String descricao;

    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;
    
}
