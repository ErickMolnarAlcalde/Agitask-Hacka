package com.example.agitask.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeRequestDTO {

    private List<Usuario> idSupervisorResponsavel;
    private List<Usuario> idUsuarioColaborador;

}
