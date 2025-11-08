package com.example.agitask.exception;

import org.springframework.data.jpa.repository.JpaRepository;

public  class UsuarioIsNotGestorException extends RuntimeException {

    public UsuarioIsNotGestorException(String message) {
        super(message);
    }

}
