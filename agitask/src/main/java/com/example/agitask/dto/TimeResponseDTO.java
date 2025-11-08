package com.example.agitask.dto;

import com.example.agitask.model.Usuario;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeResponseDTO {

    private Long idTime;
    private Long supervisorId;
    private String supervisorNome;
    private List<Usuario> colaboradores;

}
