package com.example.agitask.service;

import com.example.agitask.exception.ProjetoIdNotFoundException;
import com.example.agitask.exception.UsuarioColaboradorEmailNotFoundException; // Usada para representar "Usuário Autor não encontrado"
import com.example.agitask.dto.ComprovacaoRequestDTO;
import com.example.agitask.dto.ComprovacaoResponseDTO;
import com.example.agitask.model.Comprovacao;
import com.example.agitask.model.Projeto;
import com.example.agitask.model.Usuario;
import com.example.agitask.repository.ComprovacaoRepository;
import com.example.agitask.repository.ProjetoRepository;
import com.example.agitask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComprovacaoService {

    private final ComprovacaoRepository comprovacaoRepository;
    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;

    /**
     * Cria uma nova comprovação/comentário para um Projeto/Tarefa.
     * Requer que o Projeto e o Autor (Usuario) existam.
     */
    public ComprovacaoResponseDTO criar(ComprovacaoRequestDTO dto) {
        // 1. Busca e valida o Projeto/Tarefa
        Projeto projeto = projetoRepository.findById(dto.projetoId())
                .orElseThrow(() -> new ProjetoIdNotFoundException("Tarefa/Projeto não encontrado!")); //

        // 2. Busca e valida o Autor
        Usuario autor = usuarioRepository.findByEmail(dto.emailAutor())
                .orElseThrow(() -> new UsuarioColaboradorEmailNotFoundException("Usuário autor não encontrado!")); //
        // NOTA: Usando UsuarioColaboradorEmailNotFoundException para que seja tratada pelo GlobalHandler como 404

        // 3. Cria e preenche a entidade
        Comprovacao comprovacao = new Comprovacao();
        comprovacao.setDescricao(dto.descricao()); //
        comprovacao.setDataCriacao(LocalDateTime.now()); //
        comprovacao.setProjeto(projeto); //
        comprovacao.setAutor(autor); //

        // 4. Salva no banco de dados
        comprovacaoRepository.save(comprovacao); //

        // 5. Retorna o DTO de resposta
        return new ComprovacaoResponseDTO(
                comprovacao.getId(), //
                comprovacao.getDescricao(), //
                comprovacao.getDataCriacao(), //
                comprovacao.getAutor().getNome() //
        );
    }


    /**
     * Lista todas as comprovações/comentários associados a um Projeto/Tarefa específico.
     */
    public List<ComprovacaoResponseDTO> listarPorProjeto(UUID projetoId) {
        // 1. Busca e valida o Projeto/Tarefa
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ProjetoIdNotFoundException("Tarefa/Projeto não encontrado.")); //

        // 2. Busca as comprovações por projeto e mapeia para DTO
        return comprovacaoRepository.findByProjeto(projeto).stream() //
                .map(c -> new ComprovacaoResponseDTO(
                        c.getId(), //
                        c.getDescricao(), //
                        c.getDataCriacao(), //
                        c.getAutor().getNome() //
                ))
                .toList();
    }
}