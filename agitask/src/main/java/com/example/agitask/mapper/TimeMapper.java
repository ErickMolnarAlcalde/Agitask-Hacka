package com.example.agitask.mapper;

import com.example.agitask.dto.TimeRequestDTO;
import com.example.agitask.dto.TimeResponseDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.model.Time;
import com.example.agitask.model.TimeColaborador;
import com.example.agitask.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface TimeMapper {

    @Mapping(target = "supervisor", expression = "java(mapSupervisor(dto.getIdSupervisor()))")
    @Mapping(target = "timeColaboradores", expression = "java(mapTimeColaboradores(dto.getIdsColaboradores()))")
    Time toEntity(TimeRequestDTO dto);

    @Mapping(target = "supervisorId", source = "supervisor.id")
    @Mapping(target = "supervisorNome", source = "supervisor.nome")
    @Mapping(target = "supervisorCargo", source = "supervisor.cargo")
    @Mapping(target = "colaboradores", expression = "java(mapColaboradoresResponse(time.getTimeColaboradores()))")
    TimeResponseDTO toResponseDTO(Time time);

    List<TimeResponseDTO> toResponseDTOList(List<Time> times);

    // =====================================================
    // Métodos auxiliares
    // =====================================================

    default Usuario mapSupervisor(UUID idSupervisor) {
        if (idSupervisor == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(idSupervisor);
        return usuario;
    }

    default List<TimeColaborador> mapTimeColaboradores(List<UUID> idsColaboradores) {
        if (idsColaboradores == null) return new ArrayList<>();
        return idsColaboradores.stream()
                .map(id -> {
                    TimeColaborador tc = new TimeColaborador();
                    Usuario u = new Usuario();
                    u.setId(id);
                    tc.setColaborador(u);
                    return tc;
                })
                .collect(Collectors.toList());
    }

    default List<UsuarioResponseDTO> mapColaboradoresResponse(List<TimeColaborador> timeColaboradores) {
        if (timeColaboradores == null) return new ArrayList<>();
        return timeColaboradores.stream()
                .map(tc -> {
                    Usuario u = tc.getColaborador();
                    return UsuarioResponseDTO.builder()
                            .id(u.getId())
                            .nome(u.getNome())
                            .email(u.getEmail())
                            .ativo(u.getAtivo())
                            .ferias(u.getFerias())
                            .cargo(u.getCargo().toString())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
