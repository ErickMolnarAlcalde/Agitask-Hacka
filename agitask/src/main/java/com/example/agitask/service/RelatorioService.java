package com.example.agitask.service;

import com.example.agitask.dto.querriesDtos.*;
import com.example.agitask.model.Projeto;
import com.example.agitask.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final ProjetoRepository projetoRepository;

    // 🔹 Relatórios gerais
    public List<TarefasPorColaboradorDTO> relatorioTarefasPorColaborador() {
        return projetoRepository.countTarefasPorColaborador();
    }

    public List<TarefasPorStatusDTO> relatorioTarefasPorStatus() {
        return projetoRepository.countTarefasPorStatus();
    }

    public List<Projeto> relatorioTarefasAtrasadas() {
        return projetoRepository.findTarefasAtrasadas();
    }

    public List<RelatorioTarefaDTO> relatorioCompleto() {
        return projetoRepository.gerarRelatorioCompleto();
    }

    public FuncionarioConcluiuDTO funcionarioMaisConcluiu() {
        return projetoRepository.funcionarioMaisConcluiu(PageRequest.of(0, 1)).get(0);
    }

    public FuncionarioConcluiuDTO funcionarioMenosConcluiu() {
        return projetoRepository.funcionarioMenosConcluiu(PageRequest.of(0, 1)).get(0);
    }

    public List<EvolucaoAnualDTO> evolucaoAnual(int ano) {
        return projetoRepository.evolucaoAnual(ano);
    }

    // 🔹 Relatórios por colaborador
    public TarefasPorColaboradorDTO relatorioTarefasPorColaborador(String email) {
        return projetoRepository.countTarefasPorColaborador(email);
    }

    public List<TarefasPorStatusDTO> relatorioTarefasPorStatusDoColaborador(String email) {
        return projetoRepository.tarefasPorStatusDoColaborador(email);
    }

    public List<Projeto> tarefasDoDia(String email) {
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = hoje.plusDays(1);
        return projetoRepository.tarefasDoDia(email, hoje, amanha);
    }

    public List<Projeto> relatorioTarefasAtrasadasDoColaborador(String email) {
        return projetoRepository.tarefasAtrasadasDoColaborador(email);
    }

    public List<RelatorioTarefaDTO> relatorioCompletoPorColaborador(String email) {
        return projetoRepository.relatorioCompletoPorColaborador(email);
    }

    public List<FuncionarioConcluiuDTO> totalTarefasConcluidasPorColaborador() {
        return projetoRepository.totalTarefasConcluidasPorColaborador();
    }
}