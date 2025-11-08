package com.example.agitask.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "times")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTime;
    /*
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> idSupervisorResponsavel;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> idUsuarioColaborador;
    */

}
