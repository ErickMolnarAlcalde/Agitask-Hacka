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

    @ExceptionHandler(ProjetoIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto projetoIdNotFoundException(ProjetoIdNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(UsuarioGestorEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioGestorEmailNotFoundException(UsuarioGestorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(UsuarioColaboradorEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioColaboradorEmailNotFoundException(UsuarioColaboradorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(UsuarioSupervisorEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioSupervisorEmailNotFoundException(UsuarioSupervisorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }
    @ExceptionHandler(UsuarioIsNotGestorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto usuarioIsNotGestorException(UsuarioSupervisorEmailNotFoundException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(UsuarioIsNotGestorOrSupervisorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto UsuarioIsNotGestorOrSupervisorException(UsuarioIsNotGestorOrSupervisorException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }

    @ExceptionHandler(ProjetoIsNotTarefaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto projetoIsNotTarefaException(ProjetoIsNotTarefaException ex){
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "Por gentileza, verificar as informações fornecidas",
                LocalDateTime.now()
        );
        return errorDto;
    }
    @ExceptionHandler(ProjetoIsNotProjetoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto projetoIsNotProjetoException(ProjetoIsNotTarefaException ex){
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
