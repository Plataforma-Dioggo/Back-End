package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.NotasRequestDTO;
import com.example.plataformadioggoapi.dto.NotasResponseDTO;
import com.example.plataformadioggoapi.exception.NotaNotFoundException;
import com.example.plataformadioggoapi.mapper.AtividadeNotaMapper;
import com.example.plataformadioggoapi.mapper.NotasMapper;
import com.example.plataformadioggoapi.model.AtividadeNota;
import com.example.plataformadioggoapi.model.Notas;
import com.example.plataformadioggoapi.repository.NotasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotasService {
    private final NotasRepository notasRepository;

    private Double calcularMedia(List<Double> notas) {

        if (notas == null || notas.isEmpty()) {
            return null;
        }

        double soma = 0;

        for (Double n : notas) {
            soma += n;
        }

        return soma / notas.size();
    }

    public Notas lancarNota(String matricula,
                           String disciplinaId,
                           NotasRequestDTO request) {

        Notas nota = notasRepository
                .findByMatriculaNotaAndDisciplinaId(matricula, disciplinaId)
                .orElse(
                        Notas.builder()
                                .matriculaNota(matricula)
                                .disciplinaId(disciplinaId)
                                .notas(new ArrayList<>())
                                .build()
                );

        if (request.getNotas() != null) {
            List<AtividadeNota> novasAtividades = request.getNotas()
                    .stream()
                    .map(AtividadeNotaMapper::toEntity)
                    .toList();

            nota.getNotas().addAll(novasAtividades);
        }

        recalcularMedias(nota);

        return notasRepository.save(nota);
    }

    public List<NotasResponseDTO> buscarBoletim(String matricula) {

        List<Notas> notas = notasRepository
                .findByMatriculaNota(matricula);

        return notas.stream()
                .map(NotasMapper::toResponse)
                .toList();
    }

    public NotasResponseDTO buscarPorDisciplina(String matricula,
                                               String disciplinaId) {

        Notas nota = notasRepository
                .findByMatriculaNotaAndDisciplinaId(matricula, disciplinaId)
                .orElseThrow(() ->
                        new NotaNotFoundException("Nota não encontrada"));

        return NotasMapper.toResponse(nota);
    }

    private void recalcularMedias(Notas nota) {

        List<Double> listaN1 = new ArrayList<>();
        List<Double> listaN2 = new ArrayList<>();

        if (nota.getNotas() != null) {

            for (AtividadeNota atividade : nota.getNotas()) {

                if ("N1".equalsIgnoreCase(atividade.getAtribuicao())) {
                    listaN1.add(atividade.getNota());
                }

                if ("N2".equalsIgnoreCase(atividade.getAtribuicao())) {
                    listaN2.add(atividade.getNota());
                }
            }
        }

        nota.setNotaN1(listaN1);
        nota.setNotaN2(listaN2);

        Double mediaN1 = calcularMedia(listaN1);
        Double mediaN2 = calcularMedia(listaN2);

        nota.setMediaN1(mediaN1);
        nota.setMediaN2(mediaN2);

        nota.setMediaFinal(
                (mediaN1 != null && mediaN2 != null)
                        ? (mediaN1 + mediaN2) / 2
                        : null
        );
    }
}
