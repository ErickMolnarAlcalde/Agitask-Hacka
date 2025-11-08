package com.example.agitask.service;

import com.example.agitask.Exception.ProjetoIdNotFoundException;
import com.example.agitask.Exception.UsuarioGestorEmailNotFoundException;
import com.example.agitask.Exception.UsuarioSupervisorEmailNotFoundException;
import com.example.agitask.dto.ProjetoRequestDTO;
import com.example.agitask.dto.ProjetoResponseDTO;
import com.example.agitask.dto.ProjetoUpdateRequestDTO;
import com.example.agitask.mapper.ProjetoMapper;
import com.example.agitask.model.Projeto;
import com.example.agitask.model.Usuario;
import com.example.agitask.repository.ProjetoRepository;
import com.example.agitask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final UsuarioRepository usuarioRepository;
    private final ProjetoRepository projetoRepository;
    private final ProjetoMapper projetoMapper;

    public ProjetoResponseDTO create(ProjetoRequestDTO requestDTO){
        Projeto projeto = projetoMapper.toEntity(requestDTO);
        
        projetoRepository.save(projeto);

        ProjetoResponseDTO responseDTO = projetoMapper.toResponse(projeto);

        return responseDTO;
    }


    public ProjetoResponseDTO findById(UUID id){
        Projeto projeto = projetoRepository.findById(id).orElseThrow(()->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        ProjetoResponseDTO responseDTO = projetoMapper.toResponse(projeto);

        return responseDTO;
    }

    public List<ProjetoResponseDTO> getAll(){
        List<Projeto> projetos = projetoRepository.findAll();

        List<ProjetoResponseDTO> responseDTOs = projetos.stream().map(projetoMapper::toResponse).toList();

        return responseDTOs;
    }

    public ProjetoResponseDTO update(UUID id, ProjetoUpdateRequestDTO requestDTO) {
        Projeto projeto = projetoRepository.findById(id).orElseThrow(() ->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        if(requestDTO.titulo()!=null){
            projeto.setTitulo(requestDTO.titulo());
        }

        if(requestDTO.descricao()!=null){
            projeto.setDescricao(requestDTO.descricao());
        }

        if(requestDTO.priorizacao()!=null){
            projeto.setPriorizacao(requestDTO.priorizacao());
        }

        if(requestDTO.tipo()!=null){
            projeto.setTipo(requestDTO.tipo());
        }

        if(requestDTO.prazo()!=null){
            projeto.setPrazo(requestDTO.prazo());
        }

        if (requestDTO.emailGestor()!=null){
            Usuario usuarioGestor = usuarioRepository.findByEmail(requestDTO.emailGestor()).
                    orElseThrow(()-> new UsuarioGestorEmailNotFoundException("email do gestor não encontrado!"));
            projeto.setGestor(usuarioGestor);
        }

        if (requestDTO.emailSupervisor()!=null){
            Usuario usuarioSupervisor = usuarioRepository.findByEmail(requestDTO.emailSupervisor()).
                    orElseThrow(()-> new UsuarioSupervisorEmailNotFoundException("email do supervisor não encontrado!"));
            projeto.setGestor(usuarioSupervisor);
        }

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return projetoMapper.toResponse(projetoAtualizado);
    }


    public void delete(UUID id){
        Projeto projeto = projetoRepository.findById(id).orElseThrow(()->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        projetoRepository.delete(projeto);

    }

}
