package com.example.agitask.mapper;

import com.example.agitask.Exception.UsuarioGestorEmailNotFoundException;
import com.example.agitask.Exception.UsuarioSupervisorEmailNotFoundException;
import com.example.agitask.dto.ProjetoRequestDTO;
import com.example.agitask.dto.ProjetoResponseDTO;
import com.example.agitask.enums.Status;
import com.example.agitask.model.Projeto;
import com.example.agitask.model.Usuario;
import com.example.agitask.repository.ProjetoRepository;
import com.example.agitask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProjetoMapper {

    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;

    public Projeto toEntity(ProjetoRequestDTO requestDTO){
        Projeto projeto = new Projeto();
        projeto.setTitulo(requestDTO.titulo());
        projeto.setDescricao(requestDTO.descricao());
        projeto.setPriorizacao(requestDTO.priorizacao());
        projeto.setTipo(requestDTO.tipo());
        projeto.setPrazo(requestDTO.prazo());

        Usuario usuarioGestor = usuarioRepository.findByEmail(requestDTO.emailGestor()).
                orElseThrow(()-> new UsuarioGestorEmailNotFoundException("email do gestor não encontrado!"));
        projeto.setGestor(usuarioGestor);

        Usuario usuarioSurpevisor = usuarioRepository.findByEmail(requestDTO.emailSupervisor()).
                orElseThrow(()-> new UsuarioSupervisorEmailNotFoundException("email do surpevisor não encontrado!"));
        projeto.setGestor(usuarioSurpevisor);

        return projeto;
    }

    public ProjetoResponseDTO toResponse(Projeto projeto){
        return new ProjetoResponseDTO(projeto.getTitulo(), projeto.getDescricao(),
                Status.PENDENTE, projeto.getPriorizacao(), projeto.getTipo(),
                LocalDateTime.now(),projeto.getPrazo());
    }




}
