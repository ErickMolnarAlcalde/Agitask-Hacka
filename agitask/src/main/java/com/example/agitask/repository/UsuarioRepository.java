package com.example.agitask.repository;

import com.example.agitask.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository  extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);
}
