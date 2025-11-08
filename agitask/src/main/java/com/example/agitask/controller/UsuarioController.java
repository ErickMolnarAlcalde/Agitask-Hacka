package com.example.agitask.controller;

import com.example.agitask.dto.UsuarioLoginRequestDTO;
import com.example.agitask.dto.UsuarioRequestDTO;
import com.example.agitask.dto.UsuarioResponseDTO;
import com.example.agitask.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public boolean loginUsuario(@RequestBody UsuarioLoginRequestDTO dto) {
        return usuarioService.loginUsuario(dto);
    }

    @PostMapping("/admin")
    public ResponseEntity<UsuarioResponseDTO> criarUsuarioPorAdmin(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.criarUsuarioPorAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@PathVariable String email) {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    // TODO: Implementar endpoints de atualização de usuário

    @PatchMapping("/ferias_on/{email}")
    public ResponseEntity<UsuarioResponseDTO> ativarFerias(@PathVariable String email) {
        UsuarioResponseDTO usuario = usuarioService.entrarDeFerias(email);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PatchMapping("/ferias_off/{email}")
    public ResponseEntity<UsuarioResponseDTO> desativarFerias(@PathVariable String email) {
        UsuarioResponseDTO usuario = usuarioService.voltarDeFerias(email);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PatchMapping("/desativar/{email}")
    public ResponseEntity<UsuarioResponseDTO> desativar(@PathVariable String email) {
        UsuarioResponseDTO usuario = usuarioService.desativarUsuario(email);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PatchMapping("/reativar/{email}")
    public ResponseEntity<UsuarioResponseDTO> reativar(@PathVariable String email) {
        UsuarioResponseDTO usuario = usuarioService.ativarUsuario(email);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

}
