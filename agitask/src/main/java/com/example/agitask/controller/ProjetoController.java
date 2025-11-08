package com.example.agitask.controller;

import com.example.agitask.dto.ProjetoDeleteDto;
import com.example.agitask.dto.ProjetoRequestDTO;
import com.example.agitask.dto.ProjetoResponseDTO;
import com.example.agitask.dto.ProjetoUpdateRequestDTO;
import com.example.agitask.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @PostMapping("/criar-projeto")
    public ResponseEntity<ProjetoResponseDTO> createProjeto(@RequestBody ProjetoRequestDTO requestDTO){
        return ResponseEntity.ok().body(projetoService.createProjeto(requestDTO));
    }

    @PostMapping("/criar-tarefa")
    public ResponseEntity<ProjetoResponseDTO> createTarefa(@RequestBody ProjetoRequestDTO requestDTO){
        return ResponseEntity.ok().body(projetoService.createTarefa(requestDTO));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProjetoResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok().body(projetoService.findById(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProjetoResponseDTO>> findAll(){
        return ResponseEntity.ok().body(projetoService.getAll());
    }

    @DeleteMapping("/deletar-projeto")
    public ResponseEntity<Void> deleteProjeto(@RequestBody ProjetoDeleteDto projetoDeleteDto){
        projetoService.deleteProjeto(projetoDeleteDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar-tarefa")
    public ResponseEntity<Void> deleteTarefa(@RequestBody ProjetoDeleteDto projetoDeleteDto){
        projetoService.deleteTarefa(projetoDeleteDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/alterar-por-gestor")
    public ResponseEntity<ProjetoResponseDTO> alterarPorGestor(
                                                            @RequestBody ProjetoUpdateRequestDTO requestDTO){
        return ResponseEntity.ok().body(projetoService.updateGestor(requestDTO));
    }

    @PutMapping("/alterar-por-supervisor")
    public ResponseEntity<ProjetoResponseDTO> alterarPorSupervisor(@RequestBody ProjetoUpdateRequestDTO requestDTO){
        return ResponseEntity.ok().body(projetoService.updateSupervisor(requestDTO));
    }

    @PutMapping("/alterar-por-colaborador")
    public ResponseEntity<ProjetoResponseDTO> alterarPorColaborador(@RequestBody ProjetoUpdateRequestDTO requestDTO){
        return ResponseEntity.ok().body(projetoService.updateColaborador(requestDTO));
    }







}
