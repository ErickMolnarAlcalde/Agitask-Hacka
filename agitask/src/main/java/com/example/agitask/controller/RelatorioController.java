package com.example.agitask.controller;


import com.example.agitask.dto.querriesDtos.*;
import com.example.agitask.model.Projeto;
import com.example.agitask.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    // 🔹 Relatórios gerais
    @GetMapping("/tarefas-colaborador") // retorna total de tarefas por todos colaboradores
    public List<TarefasPorColaboradorDTO> tarefasPorColaborador() {
        return relatorioService.relatorioTarefasPorColaborador();
    }

    @GetMapping("/tarefas-status")
    public List<TarefasPorStatusDTO> tarefasPorStatus() {
        return relatorioService.relatorioTarefasPorStatus();
    }

    @GetMapping("/tarefas-atrasadas")
    public List<Projeto> tarefasAtrasadas() {
        return relatorioService.relatorioTarefasAtrasadas();
    }

    @GetMapping("/completo")
    public List<RelatorioTarefaDTO> relatorioCompleto() {
        return relatorioService.relatorioCompleto();
    }

    @GetMapping("/mais-concluiu")
    public FuncionarioConcluiuDTO funcionarioMaisConcluiu() {
        return relatorioService.funcionarioMaisConcluiu();
    }

    @GetMapping("/menos-concluiu")
    public FuncionarioConcluiuDTO funcionarioMenosConcluiu() {
        return relatorioService.funcionarioMenosConcluiu();
    }

    @GetMapping("/evolucao-anual/{ano}")
    public List<EvolucaoAnualDTO> evolucaoAnual(@PathVariable int ano) {
        return relatorioService.evolucaoAnual(ano);
    }

    // 🔹 Relatórios específicos por colaborador (email)
    @GetMapping("/tarefas-colaborador/email") // total de tarefas de um colaborador específico
    public TarefasPorColaboradorDTO tarefasPorColaborador(@RequestParam String email) {
        return relatorioService.relatorioTarefasPorColaborador(email);
    }

    @GetMapping("/tarefas-status/email") // status das tarefas de um colaborador
    public List<TarefasPorStatusDTO> tarefasPorStatusDoColaborador(@RequestParam String email) {
        return relatorioService.relatorioTarefasPorStatusDoColaborador(email);
    }

    @GetMapping("/tarefas-atrasadas/email") // tarefas atrasadas de um colaborador
    public List<Projeto> tarefasAtrasadasDoColaborador(@RequestParam String email) {
        return relatorioService.relatorioTarefasAtrasadasDoColaborador(email);
    }

    @GetMapping("/completo/email") // relatório completo de um colaborador
    public List<RelatorioTarefaDTO> relatorioCompletoPorColaborador(@RequestParam String email) {
        return relatorioService.relatorioCompletoPorColaborador(email);
    }

    @GetMapping("/tarefas-dia/email") // tarefas do dia de um colaborador
    public List<Projeto> tarefasDoDia(@RequestParam String email) {
        return relatorioService.tarefasDoDia(email);
    }

    @GetMapping("/concluidas") // total de tarefas concluídas de todos colaboradores
    public List<FuncionarioConcluiuDTO> totalTarefasConcluidasPorColaborador() {
        return relatorioService.totalTarefasConcluidasPorColaborador();
    }
}


