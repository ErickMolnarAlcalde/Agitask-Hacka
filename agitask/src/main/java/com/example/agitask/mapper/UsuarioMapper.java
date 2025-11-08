package com.example.agitask.mapper;

import com.example.agitask.dto.UsuarioRequestDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.model.Usuario;
import com.example.agitask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    // RequestDTO → Entidade
    Usuario toEntity(UsuarioRequestDTO dto);

    // Entidade → ResponseDTO
    UsuarioResponseDTO toResponseDTO(Usuario entity);

    // Lista de entidades → Lista de ResponseDTO
    List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> entities);
}
