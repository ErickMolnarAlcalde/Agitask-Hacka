package com.example.agitask.mapper;

import com.example.agitask.dto.TimeRequestDTO;
import com.example.agitask.dto.TimeResponseDTO;
import com.example.agitask.model.Time;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeMapper {

    // RequestDTO → Entidade
    Time toEntity(TimeRequestDTO dto);

    // Entidade → ResponseDTO
    TimeResponseDTO toResponseDTO(Time time);

    // Lista de entidades → Lista de ResponseDTO
    List<TimeResponseDTO> toResponseDTOList(List<Time> time);

}
