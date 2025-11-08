package com.example.agitask.repository;

import com.example.agitask.dto.querriesDtos.*;
import com.example.agitask.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    // 1Total de tarefas por colaborador
    @Query("SELECT p.colaborador.email AS email, COUNT(p) AS totalTarefas " +
            "FROM Projeto p " +
            "WHERE p.colaborador IS NOT NULL " +
            "GROUP BY p.colaborador.email")
    List<TarefasPorColaboradorDTO> countTarefasPorColaborador();

    @Query("SELECT p.colaborador.email AS email, COUNT(p) AS totalTarefas " +
            "FROM Projeto p " +
            "WHERE p.colaborador.email = :email " +
            "GROUP BY p.colaborador.email")
    TarefasPorColaboradorDTO countTarefasPorColaborador(@Param("email") String email);

    // 2Tarefas por status
    @Query("SELECT p.status AS status, COUNT(p) AS total " +
            "FROM Projeto p " +
            "GROUP BY p.status")
    List<TarefasPorStatusDTO> countTarefasPorStatus();

    // 3️Tarefas atrasadas (não concluídas)
    @Query("SELECT p FROM Projeto p " +
            "WHERE p.tipo = com.example.agitask.enums.Tipo.TAREFA " +
            "AND p.prazo < CURRENT_TIMESTAMP " +
            "AND p.status != com.example.agitask.enums.Status.CONCLUIDO")
    List<Projeto> findTarefasAtrasadas();

    //  Relatório detalhado de tarefas
    @Query("SELECT new com.example.agitask.dto.querriesDtos.RelatorioTarefaDTO(" +
            "p.titulo, p.colaborador.email, p.supervisor.email, p.gestor.email, " +
            "p.status, p.priorizacao, p.prazo, p.dataCriacao) " +
            "FROM Projeto p")
    List<RelatorioTarefaDTO> gerarRelatorioCompleto();

    // 6️Funcionário que mais concluiu tarefas
    @Query("SELECT p.colaborador.email AS email, COUNT(p) AS totalConcluidas " +
            "FROM Projeto p " +
            "WHERE p.status = com.example.agitask.enums.Status.CONCLUIDO " +
            "GROUP BY p.colaborador.email " +
            "ORDER BY totalConcluidas DESC")
    List<FuncionarioConcluiuDTO> funcionarioMaisConcluiu(org.springframework.data.domain.Pageable pageable);

    // 7️Funcionário que menos concluiu tarefas
    @Query("SELECT p.colaborador.email AS email, COUNT(p) AS totalConcluidas " +
            "FROM Projeto p " +
            "WHERE p.status = com.example.agitask.enums.Status.CONCLUIDO " +
            "GROUP BY p.colaborador.email " +
            "ORDER BY totalConcluidas ASC")
    List<FuncionarioConcluiuDTO> funcionarioMenosConcluiu(org.springframework.data.domain.Pageable pageable);

    // 8️Evolução anual de cada colaborador
    @Query("SELECT p.colaborador.email AS email, COUNT(p) AS totalConcluidasAno " +
            "FROM Projeto p " +
            "WHERE p.status = com.example.agitask.enums.Status.CONCLUIDO " +
            "AND FUNCTION('YEAR', p.dataCriacao) = :ano " +
            "GROUP BY p.colaborador.email " +
            "ORDER BY totalConcluidasAno DESC")
    List<EvolucaoAnualDTO> evolucaoAnual(@Param("ano") int ano);

    // 9️Total de tarefas concluídas por colaborador
    @Query("SELECT p.colaborador.email AS email, COUNT(p) AS totalConcluidas " +
            "FROM Projeto p " +
            "WHERE p.status = com.example.agitask.enums.Status.CONCLUIDO " +
            "GROUP BY p.colaborador.email")
    List<FuncionarioConcluiuDTO> totalTarefasConcluidasPorColaborador();

    // 10Tarefas de um colaborador por status
    @Query("SELECT p.status AS status, COUNT(p) AS total " +
            "FROM Projeto p " +
            "WHERE p.colaborador.email = :email " +
            "GROUP BY p.status")
    List<TarefasPorStatusDTO> tarefasPorStatusDoColaborador(@Param("email") String email);

    //tarefas do dia do colaborador
    @Query("SELECT p FROM Projeto p " +
            "WHERE p.colaborador.email = :email " +
            "AND p.prazo BETWEEN :inicio AND :fim")
    List<Projeto> tarefasDoDia(@Param("email") String email,
                               @Param("inicio") java.time.LocalDate inicio,
                               @Param("fim") java.time.LocalDate fim);

    // 11 Tarefas atrasadas de um colaborador
    @Query("SELECT p FROM Projeto p " +
            "WHERE p.colaborador.email = :email " +
            "AND p.tipo = com.example.agitask.enums.Tipo.TAREFA " +
            "AND p.prazo < CURRENT_TIMESTAMP " +
            "AND p.status != com.example.agitask.enums.Status.CONCLUIDO")
    List<Projeto> tarefasAtrasadasDoColaborador(@Param("email") String email);

    // 12Relatório detalhado de um colaborador
    @Query("SELECT new com.example.agitask.dto.querriesDtos.RelatorioTarefaDTO(" +
            "p.titulo, p.colaborador.email, p.supervisor.email, p.gestor.email, " +
            "p.status, p.priorizacao, p.prazo, p.dataCriacao) " +
            "FROM Projeto p " +
            "WHERE p.colaborador.email = :email")
    List<RelatorioTarefaDTO> relatorioCompletoPorColaborador(@Param("email") String email);
}

