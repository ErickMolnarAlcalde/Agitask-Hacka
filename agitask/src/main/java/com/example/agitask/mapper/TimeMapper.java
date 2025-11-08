package com.example.agitask.mapper;

import com.example.agitask.dto.TimeRequestDTO;
import com.example.agitask.dto.TimeResponseDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.model.Time;
import com.example.agitask.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface TimeMapper {

    // RequestDTO → Entidade
    @Mapping(target = "supervisor", expression = "java(mapSupervisor(dto.getIdSupervisor()))")
    @Mapping(target = "colaboradores", expression = "java(mapColaboradores(dto.getIdsColaboradores()))")
    Time toEntity(TimeRequestDTO dto);

    // Entidade → ResponseDTO
    @Mapping(target = "supervisorId", source = "supervisor.id")
    @Mapping(target = "supervisorNome", source = "supervisor.nome")
    @Mapping(target = "supervisorCargo", source = "supervisor.cargo")
    @Mapping(target = "colaboradores", expression = "java(mapColaboradoresResponse(time.getColaboradores()))")
    TimeResponseDTO toResponseDTO(Time time);

    // Lista de entidades → Lista de ResponseDTO
    List<TimeResponseDTO> toResponseDTOList(List<Time> times);

    // =====================================================
    // Métodos auxiliares (implementações default)
    // =====================================================

    default Usuario mapSupervisor(UUID idSupervisor) {
        if (idSupervisor == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(idSupervisor);
        return usuario;
    }

    default List<Usuario> mapColaboradores(List<UUID> idsColaboradores) {
        if (idsColaboradores == null) return new ArrayList<>();
        return idsColaboradores.stream()
                .map(id -> {
                    Usuario u = new Usuario();
                    u.setId(id);
                    return u;
                })
                .collect(Collectors.toList());
    }

    default List<UsuarioResponseDTO> mapColaboradoresResponse(List<Usuario> colaboradores) {
        if (colaboradores == null) return new ArrayList<>();
        return colaboradores.stream()
                .map(u -> UsuarioResponseDTO.builder()
                        .id(u.getId())
                        .nome(u.getNome())
                        .email(u.getEmail())
                        .ativo(u.getAtivo())
                        .ferias(u.getFerias())
                        .cargo(u.getCargo())
                        .build())
                .collect(Collectors.toList());
    }
}
