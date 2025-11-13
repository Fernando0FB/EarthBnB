package com.unesc.earthBnb.mapper;

import com.unesc.earthBnb.dto.ComodidadeRequest;
import com.unesc.earthBnb.dto.ComodidadeResponse;
import com.unesc.earthBnb.model.Comodidades;

public class ComodidadeMapper {

    public static Comodidades toEntity(ComodidadeRequest comodidadeRequest) {
        return Comodidades.builder()
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
            entity.setNome(req.nome());
        }
    }

}
