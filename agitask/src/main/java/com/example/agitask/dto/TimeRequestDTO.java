package com.example.agitask.dto;

import com.example.agitask.model.Usuario;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeRequestDTO {

    private Long idTime;
    private Long idSupervisor;
    private List<Long> idsColaboradores;

}
