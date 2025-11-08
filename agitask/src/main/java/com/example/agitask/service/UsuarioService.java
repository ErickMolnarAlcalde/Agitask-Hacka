package com.example.agitask.service;

import com.example.agitask.dto.UsuarioLoginRequestDTO;
import com.example.agitask.dto.UsuarioRequestDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.enums.CargoUsuario;
import com.example.agitask.mapper.UsuarioMapper;
import com.example.agitask.model.Usuario;
import com.example.agitask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario loginUsuario(UsuarioLoginRequestDTO dto) {
        Usuario usuario = buscarPorEmail(dto.getEmail());
        if (!usuario.getSenha().equals(dto.getSenha())) {
            throw new RuntimeException("Senha incorreta.");
        }
        return usuario;
    }

    @Transactional
    public UsuarioResponseDTO criarUsuarioPorAdmin(UsuarioRequestDTO usuarioRequestDTO) {
        // Verifica se email já está cadastrado
        if (emailJaCadastrado(usuarioRequestDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario novoUsuario = usuarioMapper.toEntity(usuarioRequestDTO);
        novoUsuario.setFerias(false);
        novoUsuario.setAtivo(true);
        Usuario salvo = usuarioRepository.save(novoUsuario);
        return usuarioMapper.toResponseDTO(salvo);
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {

        // Verifica se email já está cadastrado
        if (emailJaCadastrado(usuarioRequestDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        // Buscar Usuario por email
        Usuario usuario = buscarPorEmail(usuarioRequestDTO.getEmail());

        // Colaborador não pode criar usuarios
        if (usuario.getCargo().equals(CargoUsuario.COLABORADOR)) {
            throw new RuntimeException("Você não tem permissão para criar usuários.");
        }

        // Gestor pode criar supervisor e colaborador
        if (usuario.getCargo().equals(CargoUsuario.GESTOR)) {
            if (usuarioRequestDTO.getCargo().equals(usuario.getCargo())) {
                throw new RuntimeException("Gestor não pode criar outro gestor");
            } else {
                Usuario novoUsuario = usuarioMapper.toEntity(usuarioRequestDTO);
                Usuario salvo = usuarioRepository.save(novoUsuario);
                return usuarioMapper.toResponseDTO(salvo);
            }
        }

        // Supervisor pode criar apenas colaborador
        if (usuario.getCargo().equals(CargoUsuario.SUPERVISOR)) {
            if (usuarioRequestDTO.getCargo().equals(usuario.getCargo()) || usuarioRequestDTO.getCargo().equals(CargoUsuario.GESTOR)) {
                throw new RuntimeException("Supervisor não pode criar outro supervisor ou gestor");
            } else {
                Usuario novoUsuario = usuarioMapper.toEntity(usuarioRequestDTO);
                Usuario salvo = usuarioRepository.save(novoUsuario);
                return usuarioMapper.toResponseDTO(salvo);
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarUsuarioPorEmail(String email) {
        Usuario usuario = buscarPorEmail(email);
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toResponseDTOList(usuarios);
    }

    /*
    @Transactional
    public UsuarioResponseDTO atualizarUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = buscarPorEmail(dto.email());
        // Implementar atualização dos campos conforme necessário
        return null;
    }
    */

    @Transactional
    public UsuarioResponseDTO entrarDeFerias(String email) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario.getFerias()) {
            throw new RuntimeException("Usuário já está de férias.");
        }
        usuario.setFerias(true);
        Usuario emFerias = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(emFerias);
    }

    @Transactional
    public UsuarioResponseDTO voltarDeFerias(String email) {
        Usuario usuario = buscarPorEmail(email);
        if (!usuario.getFerias()) {
            throw new RuntimeException("Usuário não está de férias.");
        }
        usuario.setFerias(false);
        Usuario voltou = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(voltou);
    }

    @Transactional
    public UsuarioResponseDTO ativarUsuario(String email) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario.getAtivo()) {
            throw new RuntimeException("Usuário já está ativo.");
        }
        usuario.setAtivo(true);
        Usuario ativado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(ativado);
    }

    @Transactional
    public UsuarioResponseDTO desativarUsuario(String email) {
        Usuario usuario = buscarPorEmail(email);
        if (!usuario.getAtivo()) {
            throw new RuntimeException("Usuário já está desativado.");
        }
        usuario.setAtivo(false);
        Usuario desativado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(desativado);
    }

    private Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
    }

    private boolean emailJaCadastrado(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }
}