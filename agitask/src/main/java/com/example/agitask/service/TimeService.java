package com.example.agitask.service;

import com.example.agitask.dto.TimeRequestDTO;
import com.example.agitask.dto.TimeResponseDTO;
import com.example.agitask.mapper.TimeMapper;
import com.example.agitask.model.Time;
import com.example.agitask.repository.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimeService {

    private final TimeRepository timeRepository;
    private final TimeMapper timeMapper;

    public TimeService(TimeRepository timeRepository, TimeMapper timeMapper) {
        this.timeRepository = timeRepository;
        this.timeMapper = timeMapper;
    }

    @Transactional
    public TimeResponseDTO criarTime(TimeRequestDTO dto) {
        Time time = timeMapper.toEntity(dto);
        Time salvo = timeRepository.save(time);
        return timeMapper.toResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public TimeResponseDTO buscarTimePorId(Long idTime) {
        Time time = buscarPorId(idTime);
        return timeMapper.toResponseDTO(time);
    }

    @Transactional(readOnly = true)
    public List<TimeResponseDTO> BuscarTodosOsTimes() {
        List<Time> times = timeRepository.findAll();
        return timeMapper.toResponseDTOList(times);
    }

    // TODO: Atualizar Supervisor

    // TODO: Inserir Colaborador

    // TODO: Remover Colaborador

    @Transactional
    public void deletarTime(Long idTime) {
        Time time = buscarPorId(idTime);
        timeRepository.delete(time);
    }

    private Time buscarPorId(Long id) {
        return timeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time com o ID " + id +  " não encontrado."));
    }

}