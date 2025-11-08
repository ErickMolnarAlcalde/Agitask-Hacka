package com.example.agitask.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "time_colaboradores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeColaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_time")
    private Time time;

    @ManyToOne
    @JoinColumn(name = "id_colaborador")
    private Usuario colaborador;
}
