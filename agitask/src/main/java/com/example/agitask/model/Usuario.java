package com.example.agitask.model;

import com.example.agitask.dto.UsuarioRequestDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.enums.CargoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "Usuarios")
@Entity(name = "Usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String senha;
    private String email;

    private Boolean ativo;

    private Boolean ferias;

    @Enumerated(EnumType.STRING)
    private CargoUsuario cargo;

    /*
    @OneToMany(mappedBy = "gestor")
    private List<Tarefa> tarefaGerenciadas;

    @OneToMany(mappedBy = "supervisor")
    private List<Tarefa> tarefaSupervisionadas;

    @OneToMany(mappedBy = "colaborador")
    private List<Tarefa> tarefaExecutadas;
    */
}