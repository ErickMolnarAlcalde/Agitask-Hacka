package com.example.agitask.controller;

import com.example.agitask.dto.TimeRequestDTO;
import com.example.agitask.dto.TimeResponseDTO;
import com.example.agitask.service.TimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<TimeResponseDTO> criarTime(@RequestBody TimeRequestDTO dto) {
        TimeResponseDTO time = timeService.criarTime(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(time);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeResponseDTO> buscarTimePorId(@PathVariable Long id) {
        TimeResponseDTO time = timeService.buscarTimePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(time);
    }

    @GetMapping
    public ResponseEntity<List<TimeResponseDTO>> BuscarTodosOsTimes() {
        List<TimeResponseDTO> times = timeService.BuscarTodosOsTimes();
        return ResponseEntity.status(HttpStatus.OK).body(times);
    }

    // TODO: ENDPOINT Atualizar Supervisor

    // TODO: ENDPOINT Inserir Colaborador

    // TODO: ENDPOINT Remover Colaborador

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarTime(@PathVariable Long id) {
        timeService.deletarTime(id);
        return ResponseEntity.status(HttpStatus.OK).body("O time com o id " + id + " foi deletado com sucesso.");
    }
}
