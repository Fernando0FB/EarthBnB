package com.unesc.earthBnb.mapper;

import com.unesc.earthBnb.dto.AvaliacaoRequest;
import com.unesc.earthBnb.dto.AvaliacaoResponse;
import com.unesc.earthBnb.dto.ComodidadeRequest;
import com.unesc.earthBnb.model.Avaliacoes;

public class AvaliacaoMapper {

    public static Avaliacoes toEntity(AvaliacaoRequest request) {
        return Avaliacoes.builder()
                .reserva(request.reserva())
                .nota(request.nota())
                .comentario(request.comentario())
                .dataAvaliacao(request.dataAvaliacao())
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

    public static void merge(Avaliacoes entity, AvaliacaoRequest req) {
        if (req.reserva() != null) entity.setReserva(req.reserva());
        if (req.nota() != null) entity.setNota(req.nota());
        if (req.comentario() != null) entity.setComentario(req.comentario());
        if (req.dataAvaliacao() != null) entity.setDataAvaliacao(req.dataAvaliacao());
    }
}
