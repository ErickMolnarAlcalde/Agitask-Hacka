package com.example.agitask.repository;

import com.example.agitask.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {
}
