package com.example.agitask.mapper;

import com.example.agitask.exception.UsuarioGestorEmailNotFoundException;
import com.example.agitask.dto.UsuarioRequestDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.model.Usuario;
import com.example.agitask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final UsuarioRepository usuarioRepository;

    public Usuario toEntity(UsuarioRequestDTO requestDTO){

        Usuario usuario = new Usuario();
        usuario.setNome(requestDTO.nome());
        usuario.setEmail(requestDTO.email());
        usuario.setSenha(requestDTO.senha());
        usuario.setCargo(requestDTO.cargo());

        return usuario;
    }

    public UsuarioResponseDTO toResponse(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCargo()
        );
    }

    public Usuario toAlter(String email, UsuarioRequestDTO requestDTO){
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()->
                new UsuarioGestorEmailNotFoundException("email não encontrado"));
        usuario.setNome(requestDTO.nome());
        usuario.setEmail(requestDTO.email());
        usuario.setSenha(requestDTO.senha());
        usuario.setCargo(requestDTO.cargo());

        return usuario;
    }




}
