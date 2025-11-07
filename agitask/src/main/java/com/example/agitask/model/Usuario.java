package com.example.agitask.model;

import com.example.agitask.dto.UsuarioRequestDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.enums.CargoUsuario;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Table(name = "Usuarios")
@Entity(name = "Usuarios")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String senha;
    private String email;

    @Enumerated(EnumType.STRING)
    private CargoUsuario cargo;

    @OneToMany(mappedBy = "gestor")
    private List<Tarefa> tarefaGerenciadas;

    @OneToMany(mappedBy = "supervisor")
    private List<Tarefa> tarefaSupervisionadas;

    @OneToMany(mappedBy = "colaborador")
    private List<Tarefa> tarefaExecutadas;

    public Usuario(UsuarioRequestDTO dados) {
        this.nome = dados.nome();
        this.senha = dados.senha();
        this.email = dados.email();
        this.cargo = dados.cargo();
    }

    public Usuario(UsuarioResponseDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cargo = dados.cargo();
    }
}