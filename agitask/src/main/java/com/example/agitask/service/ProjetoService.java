package com.example.agitask.service;

import com.example.agitask.Exception.*;
import com.example.agitask.dto.ProjetoDeleteDto;
import com.example.agitask.dto.ProjetoRequestDTO;
import com.example.agitask.dto.ProjetoResponseDTO;
import com.example.agitask.dto.ProjetoUpdateRequestDTO;
import com.example.agitask.enums.CargoUsuario;
import com.example.agitask.enums.Tipo;
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

    public ProjetoResponseDTO createProjeto(ProjetoRequestDTO requestDTO){

        Projeto projeto = projetoMapper.toEntity(requestDTO);

        if (projeto.getGestor().getCargo() == CargoUsuario.GESTOR && projeto.getGestor().getSenha().equals(requestDTO.senha())) {
            projetoRepository.save(projeto);
            ProjetoResponseDTO responseDTO = projetoMapper.toResponse(projeto);

            return responseDTO;
        }else{
            throw new UsuarioIsNotGestorException("Apenas gestor pode criar projetos!");
        }

    }

    public ProjetoResponseDTO createTarefa(ProjetoRequestDTO requestDTO){

        Projeto projeto = projetoMapper.toEntity(requestDTO);


        if (projeto.getSupervisor().getCargo() == CargoUsuario.SUPERVISOR
                && projeto.getSupervisor().getSenha().equals(requestDTO.senha())
                || projeto.getGestor().getCargo() == CargoUsuario.GESTOR
                && projeto.getGestor().getSenha().equals(requestDTO.senha())) {
            projetoRepository.save(projeto);
            ProjetoResponseDTO responseDTO = projetoMapper.toResponse(projeto);

            return responseDTO;
        }else{
            throw new UsuarioIsNotGestorOrSupervisorException("Apenas gestor ou supervisor pode criar tarefas!");
        }

    }

    public ProjetoResponseDTO updateGestor(UUID id, ProjetoUpdateRequestDTO requestDTO) {
        Projeto projeto = projetoRepository.findById(id).orElseThrow(() ->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        Usuario usuario = usuarioRepository.findByEmail(requestDTO.email()).orElseThrow(()->
                new UsuarioGestorEmailNotFoundException("Email do gestor não encontrado!"));
        if (usuario.getCargo() == CargoUsuario.GESTOR && usuario.getSenha().equals(requestDTO.senha())) {

            if (requestDTO.titulo() != null) {
                projeto.setTitulo(requestDTO.titulo());
            }

            if (requestDTO.descricao() != null) {
                projeto.setDescricao(requestDTO.descricao());
            }

            if (requestDTO.status() != null) {
                projeto.setStatus(requestDTO.status());
            }

            if (requestDTO.priorizacao() != null) {
                projeto.setPriorizacao(requestDTO.priorizacao());
            }

            if (requestDTO.tipo() != null) {
                projeto.setTipo(requestDTO.tipo());
            }

            if (requestDTO.prazo() != null) {
                projeto.setPrazo(requestDTO.prazo());
            }

            if (requestDTO.emailGestor() != null) {
                Usuario usuarioGestor = usuarioRepository.findByEmail(requestDTO.email()).
                        orElseThrow(() -> new UsuarioGestorEmailNotFoundException("email do gestor não encontrado!"));
                projeto.setGestor(usuarioGestor);
            }
            if (requestDTO.emailSupervisor() != null) {
                Usuario usuarioSupervisor = usuarioRepository.findByEmail(requestDTO.email()).
                        orElseThrow(() -> new UsuarioGestorEmailNotFoundException("email do gestor não encontrado!"));
                projeto.setGestor(usuarioSupervisor);
            }

        }

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return projetoMapper.toResponse(projetoAtualizado);
    }

    public ProjetoResponseDTO updateSupervisor(UUID id, ProjetoUpdateRequestDTO requestDTO) {
        Projeto projeto = projetoRepository.findById(id).orElseThrow(() ->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        Usuario usuario = usuarioRepository.findByEmail(requestDTO.email()).orElseThrow(()->
                new UsuarioGestorEmailNotFoundException("Email do supervisor não encontrado!"));
        if (usuario.getCargo() == CargoUsuario.SUPERVISOR
                && usuario.getSenha().equals(requestDTO.senha())
                && projeto.getTipo() == Tipo.TAREFA) {

            if (requestDTO.titulo() != null) {
                projeto.setTitulo(requestDTO.titulo());
            }

            if (requestDTO.descricao() != null) {
                projeto.setDescricao(requestDTO.descricao());
            }

            if (requestDTO.status() != null) {
                projeto.setStatus(requestDTO.status());
            }

            if (requestDTO.priorizacao() != null) {
                projeto.setPriorizacao(requestDTO.priorizacao());
            }

            if (requestDTO.tipo() != null) {
                projeto.setTipo(requestDTO.tipo());
            }

            if (requestDTO.prazo() != null) {
                projeto.setPrazo(requestDTO.prazo());
            }

            if (requestDTO.emailGestor() != null) {
                Usuario usuarioGestor = usuarioRepository.findByEmail(requestDTO.email()).
                        orElseThrow(() -> new UsuarioGestorEmailNotFoundException("email do gestor não encontrado!"));
                projeto.setGestor(usuarioGestor);
            }
            if (requestDTO.emailSupervisor() != null) {
                Usuario usuarioSupervisor = usuarioRepository.findByEmail(requestDTO.email()).
                        orElseThrow(() -> new UsuarioGestorEmailNotFoundException("email do gestor não encontrado!"));
                projeto.setGestor(usuarioSupervisor);
            }

        }

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return projetoMapper.toResponse(projetoAtualizado);
    }

    public ProjetoResponseDTO updateColaborador(UUID id, ProjetoUpdateRequestDTO requestDTO) {
        Projeto projeto = projetoRepository.findById(id).orElseThrow(() ->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        Usuario usuario = usuarioRepository.findByEmail(requestDTO.email()).orElseThrow(()->
                new UsuarioGestorEmailNotFoundException("Email do colaborador não encontrado!"));
        if (usuario.getCargo() == CargoUsuario.COLABORADOR
                && usuario.getSenha().equals(requestDTO.senha())
               ) {

            if (requestDTO.status() != null) {
                projeto.setStatus(requestDTO.status());
            }

        }

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return projetoMapper.toResponse(projetoAtualizado);
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

    public void deleteProjeto(ProjetoDeleteDto projetoDeleteDto){

        Usuario usuarioGestor = usuarioRepository.findByEmail(projetoDeleteDto.email()).orElseThrow(()->
                new UsuarioGestorEmailNotFoundException("Email do gestor não encontrado!"));
        if (usuarioGestor.getCargo() == CargoUsuario.GESTOR && usuarioGestor.getSenha().equals(projetoDeleteDto.senha())) {
            Projeto projeto = projetoRepository.findById(projetoDeleteDto.id()).orElseThrow(()->
                            new ProjetoIdNotFoundException("Id do projeto não encontrado"));

            projetoRepository.delete(projeto);

        }else{
            throw new UsuarioIsNotGestorException("Apenas gestor pode deletar projetos!");
        }
    }

    public void deleteTarefa(ProjetoDeleteDto projetoDeleteDto){

        Usuario usuarioGestor = usuarioRepository.findByEmail(projetoDeleteDto.email()).orElseThrow(()->
                new UsuarioGestorEmailNotFoundException("Email do gestor não encontrado!"));

        Usuario usuarioSupervisor = usuarioRepository.findByEmail(projetoDeleteDto.email()).orElseThrow(()->
                new UsuarioSupervisorEmailNotFoundException("Email do supervisor não encontrado!"));
        if (usuarioSupervisor.getCargo() == CargoUsuario.SUPERVISOR
                && usuarioSupervisor.getSenha().equals(projetoDeleteDto.senha()) ||
        usuarioGestor.getCargo() == CargoUsuario.GESTOR
                && usuarioGestor.getSenha().equals(projetoDeleteDto.senha())) {
            Projeto projeto = projetoRepository.findById(projetoDeleteDto.id()).orElseThrow(()->
                    new ProjetoIdNotFoundException("Id do projeto não encontrado"));

            projetoRepository.delete(projeto);

        }else{
            throw new UsuarioIsNotGestorException("Apenas gestor pode deletar projetos!");
        }
    }




}
