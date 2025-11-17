package com.unesc.earthBnb.mapper;

import com.unesc.earthBnb.dto.request.AcomodacaoPostRequest;
import com.unesc.earthBnb.dto.request.AcomodacaoPutRequest;
import com.unesc.earthBnb.dto.response.AcomodacaoResponse;
import com.unesc.earthBnb.model.Acomodacoes;

public class AcomodacaoMapper {

    public static AcomodacaoResponse toResponse(com.unesc.earthBnb.model.Acomodacoes acomodacao) {
        return new AcomodacaoResponse(
                acomodacao.getId(),
                acomodacao.getTitulo(),
                acomodacao.getDescricao(),
                acomodacao.getPrecoDiaria(),
                acomodacao.getEnderecoCompleto(),
                acomodacao.getMaxHospedes(),
                acomodacao.getQuantidadeQuartos(),
                acomodacao.getQuantidadeBanheiros(),
                acomodacao.getAceitaPets(),
                acomodacao.getComodidades().stream().map(ComodidadeMapper::toResponse).toList(),
                acomodacao.getTipoAcomodacao()
        );
    }

    public static Acomodacoes toEntity(AcomodacaoPostRequest acomodacaoPostRequest) {
        return new Acomodacoes(
                null,
                acomodacaoPostRequest.titulo(),
                acomodacaoPostRequest.descricao(),
                acomodacaoPostRequest.precoDiaria(),
                acomodacaoPostRequest.enderecoCompleto(),
                acomodacaoPostRequest.maxHospedes(),
                acomodacaoPostRequest.quantidadeQuartos(),
                acomodacaoPostRequest.quantidadeBanheiros(),
                acomodacaoPostRequest.aceitaPets(),
                acomodacaoPostRequest.comodidades().stream().map(ComodidadeMapper::toEntity).toList(),
                acomodacaoPostRequest.tipoAcomodacao()
        );
    }

    public static Acomodacoes merge(Acomodacoes entity, AcomodacaoPutRequest req) {
        if(req.titulo() != null) entity.setTitulo(req.titulo());
        if(req.descricao() != null) entity.setDescricao(req.descricao());
        if(req.precoDiaria() != null) entity.setPrecoDiaria(req.precoDiaria());
        if(req.enderecoCompleto() != null) entity.setEnderecoCompleto(req.enderecoCompleto());
        if(req.maxHospedes() != null) entity.setMaxHospedes(req.maxHospedes());
        if(req.quantidadeQuartos() != null) entity.setQuantidadeQuartos(req.quantidadeQuartos());
        if(req.quantidadeBanheiros() != null) entity.setQuantidadeBanheiros(req.quantidadeBanheiros());
        if(req.aceitaPets() != null) entity.setAceitaPets(req.aceitaPets());
        if(req.tipoAcomodacao() != null) entity.setTipoAcomodacao(req.tipoAcomodacao());
        return entity;
    }
}
