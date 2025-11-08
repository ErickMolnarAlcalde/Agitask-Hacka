package com.example.agitask.service;

import com.example.agitask.dto.UsuarioRequestDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.mapper.UsuarioMapper;
import com.example.agitask.model.Usuario;
import com.example.agitask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO create(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);

        usuarioRepository.save(usuario);

        UsuarioResponseDTO responseDTO = usuarioMapper.toResponse(usuario);

        return responseDTO;
    }




}
