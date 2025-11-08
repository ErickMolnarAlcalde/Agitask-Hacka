package com.example.agitask.repository;

import com.example.agitask.model.Comprovacao;
import com.example.agitask.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.UUID;

public interface ComprovacaoRepository extends JpaRepository<Comprovacao, UUID> {
    List<Comprovacao> findByProjeto(Projeto projeto);
}