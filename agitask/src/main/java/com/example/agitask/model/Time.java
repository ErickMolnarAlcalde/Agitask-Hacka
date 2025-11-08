package com.example.agitask.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "times")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTime;

    @ManyToOne
    @JoinColumn(name = "id_supervisor")
    private Usuario supervisor;

    @ManyToMany
    @JoinTable(
            name = "time_colaboradores",
            joinColumns = @JoinColumn(name = "id_time"),
            inverseJoinColumns = @JoinColumn(name = "id_colaborador")
    )
    private List<Usuario> colaboradores = new ArrayList<>();

    public void adicionarColaborador(Usuario usuario) {
        this.colaboradores.add(usuario);
    }

    public void removerColaborador(Usuario usuario) {
        this.colaboradores.remove(usuario);
    }

}
