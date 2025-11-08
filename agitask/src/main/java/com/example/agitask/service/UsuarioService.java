package com.example.agitask.service;

import com.example.agitask.dto.ProjetoRequest;
import com.example.agitask.dto.UsuarioRequestDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.enums.CargoUsuario;
import com.example.agitask.mapper.UsuarioMapper;
import com.example.agitask.model.Projeto;
import com.example.agitask.model.Usuario;
import com.example.agitask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO){

        // Buscar Usuario por email
        if (usuarioRepository.findByEmail(usuarioRequestDTO.emailCargo()).isPresent()) {
            throw new RuntimeException("Usuário já cadastrado com esse e-mail.");
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);

        // Admin pode criar qualquer usuario


        // Gestor pode criar supervisor e colaborador
        if (usuario.getCargo().equals(CargoUsuario.GESTOR)) {
            if(usuarioRepository.findByEmail(usuarioRequestDTO.email()).isPresent()) {
                throw new RuntimeException("Email já cadastrado");
            } else if (usuarioRequestDTO.cargo().equals(usuario.getCargo())) {
                    throw new RuntimeException("Gestor não pode criar outro gestor");
            } else {
                Usuario novoUsuario = usuarioMapper.toEntity(usuarioRequestDTO);
                // se precisar implementar uma lógica específica para gestor, fazer aqui
                Usuario salvo = usuarioRepository.save(novoUsuario);
                return usuarioMapper.toResponse(salvo);
            }
        }

        // Supervisor pode criar apenas colaborador
        if (usuario.getCargo().equals(CargoUsuario.SUPERVISOR)) {
            if(usuarioRepository.findByEmail(usuarioRequestDTO.email()).isPresent()) {
                throw new RuntimeException("Email já cadastrado");
            } else if (usuarioRequestDTO.cargo().equals(usuario.getCargo()) || usuarioRequestDTO.cargo().equals(CargoUsuario.GESTOR)) {
                throw new RuntimeException("Supervisor não pode criar outro supervisor ou gestor");
            } else {
                Usuario novoUsuario = usuarioMapper.toEntity(usuarioRequestDTO);
                // se precisar implementar uma lógica específica para supervisor, fazer aqui
                Usuario salvo = usuarioRepository.save(novoUsuario);
                return usuarioMapper.toResponse(salvo);
            }
        }

        // Colaborador não pode criar usuarios
        if (usuario.getCargo().equals(CargoUsuario.COLABORADOR)) {
            throw new RuntimeException("Você não tem permissão para criar usuários.");
        }

return null;
    }
}
