package com.example.agitask.service;

import com.example.agitask.Exception.ProjetoIdNotFoundException;
import com.example.agitask.dto.ProjetoRequestDTO;
import com.example.agitask.dto.ProjetoResponseDTO;
import com.example.agitask.mapper.ProjetoMapper;
import com.example.agitask.model.Projeto;
import com.example.agitask.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjetoService {

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

    public void delete(UUID id){
        Projeto projeto = projetoRepository.findById(id).orElseThrow(()->
                new ProjetoIdNotFoundException("id do projeto não encontrado"));

        projetoRepository.delete(projeto);

    }

}
