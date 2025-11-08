package com.example.agitask.service;

import com.example.agitask.exception.*;
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

        if(!(projeto.getTipo() == Tipo.PROJETO)){
            throw new ProjetoIsNotProjetoException("Somente criação de projetos são possiveis neste serviço!");
        }

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
        Projeto projetoPai = projetoRepository.findById(requestDTO.projetoPai()).orElseThrow(()->
                new ProjetoIdNotFoundException("Id do projeto não encontrado!"));
        projeto.getProjetoPai();
        projeto.setProjetoPai(projetoPai);

        if(!(projeto.getTipo() == Tipo.TAREFA)){
            throw new ProjetoIsNotTarefaException("Somente criação de tarefas são possiveis neste serviço!");
        }

        if (requestDTO.emailColaborador() != null) {
            Usuario colaborador = usuarioRepository.findByEmail(requestDTO.emailColaborador())
                    .orElseThrow(() -> new UsuarioColaboradorEmailNotFoundException(
                            "email do colaborador não encontrado"));
            projeto.setColaborador(colaborador);
        }

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

    public ProjetoResponseDTO updateGestor(ProjetoUpdateRequestDTO requestDTO) {
        Projeto projeto = projetoRepository.findById(requestDTO.id()).orElseThrow(() ->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        Usuario usuario = usuarioRepository.findByEmail(requestDTO.email()).orElseThrow(()->
                new UsuarioGestorEmailNotFoundException("Email do gestor não encontrado!"));
        if(!(usuario.getCargo()==CargoUsuario.GESTOR && usuario.getSenha().equals(requestDTO.senha()))){
            throw new UsuarioIsNotGestorException("Apenas gestores podem fazer updates neste serviço");
        }

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
                Usuario usuarioGestor = usuarioRepository.findByEmail(requestDTO.emailGestor()).
                        orElseThrow(() -> new UsuarioGestorEmailNotFoundException("email do gestor não encontrado!"));
                projeto.setGestor(usuarioGestor);
            }
            if (requestDTO.emailSupervisor() != null) {
                Usuario usuarioSupervisor = usuarioRepository.findByEmail(requestDTO.emailSupervisor()).
                        orElseThrow(() -> new UsuarioSupervisorEmailNotFoundException("email do supervisor não encontrado!"));
                projeto.setGestor(usuarioSupervisor);
            }

        }

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return projetoMapper.toResponse(projetoAtualizado);
    }

    public ProjetoResponseDTO updateSupervisor(ProjetoUpdateRequestDTO requestDTO) {
        Projeto projeto = projetoRepository.findById(requestDTO.id()).orElseThrow(() ->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        Usuario usuario = usuarioRepository.findByEmail(requestDTO.email()).orElseThrow(()->
                new UsuarioGestorEmailNotFoundException("Email do supervisor não encontrado!"));
        if(!(usuario.getCargo()==CargoUsuario.GESTOR && usuario.getSenha().equals(requestDTO.senha())
                || usuario.getCargo()==CargoUsuario.SUPERVISOR && usuario.getSenha().equals(requestDTO.senha()))){
            throw new UsuarioIsNotGestorOrSupervisorException("Apenas gestores ou supervisores podem fazer updates neste serviço");
        }

            if(!(projeto.getTipo() ==Tipo.TAREFA)){
                throw  new ProjetoIsNotTarefaException("Apenas tarefas podem ser alterados por este serviço!");
            }


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
                Usuario usuarioGestor = usuarioRepository.findByEmail(requestDTO.emailGestor()).
                        orElseThrow(() -> new UsuarioGestorEmailNotFoundException("email do gestor não encontrado!"));
                projeto.setGestor(usuarioGestor);
            }
            if (requestDTO.emailSupervisor() != null) {
                Usuario usuarioSupervisor = usuarioRepository.findByEmail(requestDTO.emailSupervisor()).
                        orElseThrow(() -> new UsuarioSupervisorEmailNotFoundException("email do supervisor não encontrado!"));
                projeto.setSupervisor(usuarioSupervisor);
            }

        }

        Projeto projetoAtualizado = projetoRepository.save(projeto);

        return projetoMapper.toResponse(projetoAtualizado);
    }

    public ProjetoResponseDTO updateColaborador(ProjetoUpdateRequestDTO requestDTO) {
        Projeto projeto = projetoRepository.findById(requestDTO.id()).orElseThrow(() ->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        Usuario usuario = usuarioRepository.findByEmail(requestDTO.email()).orElseThrow(()->
                new UsuarioGestorEmailNotFoundException("Email do colaborador não encontrado!"));

        if(!(projeto.getTipo() ==Tipo.TAREFA)){
            throw  new ProjetoIsNotTarefaException("Apenas tarefas podem ser alterados por este serviço!");
        }
            if (requestDTO.status() != null) {
                projeto.setStatus(requestDTO.status());
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

        Projeto projeto1 = projetoRepository.findById(projetoDeleteDto.id()).orElseThrow(()->
                new ProjetoIdNotFoundException("Id do projeto não encontrado!"));

        if(!(projeto1.getTipo() ==Tipo.PROJETO)){
            throw  new ProjetoIsNotTarefaException("Apenas projetos podem ser deletados por este serviço!");
        }

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

        Projeto projeto1 = projetoRepository.findById(projetoDeleteDto.id()).orElseThrow(()->
                new ProjetoIdNotFoundException("Id do projeto não encontrado!"));

        if(!(projeto1.getTipo() ==Tipo.TAREFA)){
            throw  new ProjetoIsNotTarefaException("Projetos não podem ser deletados por este serviço!");
        }

        if (usuarioSupervisor.getCargo() == CargoUsuario.SUPERVISOR
                && usuarioSupervisor.getSenha().equals(projetoDeleteDto.senha()) ||
        usuarioGestor.getCargo() == CargoUsuario.GESTOR
                && usuarioGestor.getSenha().equals(projetoDeleteDto.senha())) {
            Projeto projeto = projetoRepository.findById(projetoDeleteDto.id()).orElseThrow(()->
                    new ProjetoIdNotFoundException("Id do projeto não encontrado"));

            projetoRepository.delete(projeto);

        }else{
            throw new UsuarioIsNotGestorOrSupervisorException("Apenas gestor ou supervisor pode deletar tarefas!");
        }
    }




}
