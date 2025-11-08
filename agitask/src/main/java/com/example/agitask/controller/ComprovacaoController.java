package com.example.agitask.controller;

import com.example.agitask.dto.ComprovacaoRequestDTO;
import com.example.agitask.dto.ComprovacaoResponseDTO;
import com.example.agitask.service.ComprovacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comprovacoes")
@RequiredArgsConstructor
public class ComprovacaoController {

    private final ComprovacaoService comprovacaoService;

    /**
     * POST /comprovacoes
     * Cria uma nova comprovação. Retorna 201 CREATED.
     */
    @PostMapping
    public ResponseEntity<ComprovacaoResponseDTO> criar(@RequestBody ComprovacaoRequestDTO dto) {
        ComprovacaoResponseDTO response = comprovacaoService.criar(dto); //
        return ResponseEntity.status(HttpStatus.CREATED).body(response); //
    }

    /**
     * GET /comprovacoes/projeto/{projetoId}
     * Lista todas as comprovações para um Projeto/Tarefa específico. Retorna 200 OK.
     */
    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<List<ComprovacaoResponseDTO>> listarPorProjeto(@PathVariable UUID projetoId) {
        List<ComprovacaoResponseDTO> response = comprovacaoService.listarPorProjeto(projetoId); //
        return ResponseEntity.ok(response); //
    }
}