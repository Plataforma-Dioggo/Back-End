package com.example.plataformadioggoapi.controller;

import com.example.plataformadioggoapi.dto.AlunoRequestDTO;
import com.example.plataformadioggoapi.dto.AlunoResponseDTO;
import com.example.plataformadioggoapi.dto.ObservacaoRequestDTO;
import com.example.plataformadioggoapi.dto.ObservacaoResponseDTO;
import com.example.plataformadioggoapi.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping("/listarAlunos")
    public ResponseEntity<List<AlunoResponseDTO>> buscarTodosAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> cadastrarAluno(
            @RequestBody AlunoRequestDTO alunoRequestDTO) {

        AlunoResponseDTO aluno = alunoService.cadastrarAluno(alunoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(aluno);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoResponseDTO> buscarPorMatricula(
            @PathVariable String matricula) {

        return ResponseEntity.ok(alunoService.buscarPorMatricula(matricula));
    }

    @PatchMapping("/atualizarParcial/{matricula}")
    public ResponseEntity<AlunoResponseDTO> atualizarParcial(
            @PathVariable String matricula,
            @RequestBody AlunoRequestDTO alunoRequestDTO) {

        return ResponseEntity.ok(
                alunoService.editarAluno(matricula, alunoRequestDTO)
        );
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<AlunoResponseDTO> excluirAluno(
            @PathVariable String matricula) {

        return ResponseEntity.ok(
                alunoService.excluirAluno(matricula)
        );
    }

    @PostMapping("/{matricula}/observacoes")
    public ResponseEntity<Void> adicionarObservacao(
            @PathVariable String matricula,
            @RequestBody ObservacaoRequestDTO request) {

        alunoService.adicionarObservacao(matricula, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{matricula}/observacoes")
    public ResponseEntity<List<ObservacaoResponseDTO>> listarObservacoes(
            @PathVariable String matricula) {

        return ResponseEntity.ok(
                alunoService.listarObservacoes(matricula)
        );
    }
}