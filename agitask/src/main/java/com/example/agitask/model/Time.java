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

    // Associação com TimeColaborador
    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeColaborador> timeColaboradores = new ArrayList<>();

    // Adiciona colaborador ao time criando o vínculo
    public void adicionarColaborador(Usuario usuario) {
        TimeColaborador tc = new TimeColaborador();
        tc.setTime(this);
        tc.setColaborador(usuario);
        this.timeColaboradores.add(tc);
    }

    // Remove colaborador do time
    public void removerColaborador(Usuario usuario) {
        timeColaboradores.removeIf(tc -> tc.getColaborador().equals(usuario));
    }
}
