package com.unesc.earthBnb.mapper;

import com.unesc.earthBnb.dto.request.ComodidadeRequest;
import com.unesc.earthBnb.dto.response.ComodidadeResponse;
import com.unesc.earthBnb.model.Comodidades;

public class ComodidadeMapper {

    public static Comodidades toEntity(ComodidadeRequest comodidadeRequest) {
        return Comodidades.builder()
                .id(comodidadeRequest.id())
                .nome(comodidadeRequest.nome())
                .build();
    }

    public static ComodidadeResponse toResponse(Comodidades comodidades) {
        return new ComodidadeResponse(
                comodidades.getId(),
                comodidades.getNome()
        );
    }

    public static void merge(Comodidades entity, ComodidadeRequest req) {
        if (req.nome() !=  null) {
            entity.setId(req.id());
            entity.setNome(req.nome());
        }
    }

}
