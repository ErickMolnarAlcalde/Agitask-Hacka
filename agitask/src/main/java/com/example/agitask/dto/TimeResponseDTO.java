package com.example.agitask.dto;

import com.example.agitask.enums.CargoUsuario;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeResponseDTO {

    private Long idTime;

    private UUID supervisorId;
    private String supervisorNome;
    private CargoUsuario supervisorCargo;

    private List<UsuarioResponseDTO> colaboradores;

}