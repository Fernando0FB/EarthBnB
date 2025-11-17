package com.unesc.earthBnb.mapper;

import com.unesc.earthBnb.dto.request.AvaliacaoPostRequest;
import com.unesc.earthBnb.dto.request.AvaliacaoPutRequest;
import com.unesc.earthBnb.dto.response.AvaliacaoResponse;
import com.unesc.earthBnb.model.Avaliacoes;

import java.time.LocalDateTime;

public class AvaliacaoMapper {

    public static Avaliacoes toEntity(AvaliacaoPostRequest request) {
        return Avaliacoes.builder()
                .nota(request.nota())
                .comentario(request.comentario())
                .dataAvaliacao(LocalDateTime.now())
                .build();
    }

    public static AvaliacaoResponse toResponse(Avaliacoes avaliacao) {
        return new AvaliacaoResponse(
                avaliacao.getId(),
                avaliacao.getReserva(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getDataAvaliacao()
        );
    }

    public static void merge(Avaliacoes entity, AvaliacaoPutRequest req) {
        if (req.nota() != null) entity.setNota(req.nota());
        if (req.comentario() != null) entity.setComentario(req.comentario());
        entity.setDataAvaliacao(LocalDateTime.now());
    }
}
