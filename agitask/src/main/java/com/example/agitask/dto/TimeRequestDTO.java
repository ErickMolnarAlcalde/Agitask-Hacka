package com.example.agitask.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeRequestDTO {

    private Long idTime;
    private UUID idSupervisor;
    private List<UUID> idsColaboradores;

}