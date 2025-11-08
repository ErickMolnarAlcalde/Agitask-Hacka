package com.example.agitask.exception;

import com.example.agitask.exception.*;
import com.example.agitask.dto.ErrorDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(com.example.agitask.exception.ProjetoIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto projetoIdNotFoundException(com.example.agitask.exception.ProjetoIdNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.exception.UsuarioGestorEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioGestorEmailNotFoundException(com.example.agitask.exception.UsuarioGestorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.exception.UsuarioColaboradorEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioColaboradorEmailNotFoundException(com.example.agitask.exception.UsuarioColaboradorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }
    @ExceptionHandler(UsuarioEmailNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioEmailNotFound(UsuarioEmailNotFound ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.exception.UsuarioSupervisorEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioSupervisorEmailNotFoundException(com.example.agitask.exception.UsuarioSupervisorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }
    @ExceptionHandler(com.example.agitask.exception.UsuarioIsNotGestorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioIsNotGestorException(com.example.agitask.exception.UsuarioSupervisorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.exception.UsuarioIsNotGestorOrSupervisorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto UsuarioIsNotGestorOrSupervisorException(com.example.agitask.exception.UsuarioIsNotGestorOrSupervisorException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.exception.ProjetoIsNotTarefaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto projetoIsNotTarefaException(com.example.agitask.exception.ProjetoIsNotTarefaException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }
    @ExceptionHandler(com.example.agitask.exception.ProjetoIsNotProjetoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto projetoIsNotProjetoException(com.example.agitask.exception.ProjetoIsNotTarefaException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }


    //testa erro de integridade
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> handleDataIntegrity(DataIntegrityViolationException ex) {

        String message = "Violação de integridade: valor duplicado detectado.";

        if (ex.getMessage().contains("tb_funcionario_email_key")) {
            message = "O email informado já está cadastrado.";
        } else if (ex.getMessage().contains("tb_funcionario_cpf_key")) {
            message = "O CPF informado já está cadastrado.";
        }

        ErrorDto errorDto = new ErrorDto(
                message,
                "Verifique os dados e tente novamente.",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto); // 409 CONFLICT
    }

    //Serve para pegar erros genericos
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntime(RuntimeException ex) {
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Erro interno no servidor. Tente novamente ou contate suporte.",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

}
