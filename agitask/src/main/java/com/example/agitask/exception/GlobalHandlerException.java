package com.example.agitask.exception;

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

    @ExceptionHandler(com.example.agitask.Exception.ProjetoIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto projetoIdNotFoundException(com.example.agitask.Exception.ProjetoIdNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.Exception.UsuarioGestorEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioGestorEmailNotFoundException(com.example.agitask.Exception.UsuarioGestorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.Exception.UsuarioColaboradorEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioColaboradorEmailNotFoundException(com.example.agitask.Exception.UsuarioColaboradorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.Exception.UsuarioSupervisorEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioSupervisorEmailNotFoundException(com.example.agitask.Exception.UsuarioSupervisorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }
    @ExceptionHandler(com.example.agitask.Exception.UsuarioIsNotGestorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioIsNotGestorException(com.example.agitask.Exception.UsuarioSupervisorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.Exception.UsuarioIsNotGestorOrSupervisorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto UsuarioIsNotGestorOrSupervisorException(com.example.agitask.Exception.UsuarioIsNotGestorOrSupervisorException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(com.example.agitask.Exception.ProjetoIsNotTarefaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto projetoIsNotTarefaException(com.example.agitask.Exception.ProjetoIsNotTarefaException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }
    @ExceptionHandler(com.example.agitask.Exception.ProjetoIsNotProjetoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto projetoIsNotProjetoException(com.example.agitask.Exception.ProjetoIsNotTarefaException ex){
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

        String lowerMsg = ex.getMessage().toLowerCase();

        if (lowerMsg.contains("tb_usuario_email_key")) {
            message = "O e-mail informado já está cadastrado para outro usuário.";
        } else if (lowerMsg.contains("tb_usuario_cpf_key")) {
            message = "O CPF informado já está cadastrado.";
        } else if (lowerMsg.contains("tb_tarefa_titulo_key")) {
            message = "Já existe uma tarefa com este título.";
        } else if (lowerMsg.contains("tb_comprovacao")) {
            message = "Erro ao registrar a comprovação — verifique se a tarefa existe e os dados estão corretos.";
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
