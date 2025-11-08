package com.example.agitask.mapper;

import com.example.agitask.dto.UsuarioLoginResponseDTO;
import com.example.agitask.dto.UsuarioRequestDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    // RequestDTO → Entidade
    Usuario toEntity(UsuarioRequestDTO dto);

    // Entidade → ResponseDTO
    UsuarioResponseDTO toResponseDTO(Usuario entity);
    UsuarioLoginResponseDTO toLoginResponseDTO(Usuario entity);

    // Lista de entidades → Lista de ResponseDTO
    List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> entities);
}