package com.unesc.earthBnb.dto.request;

import com.unesc.earthBnb.enums.TipoAcomodacoes;

import java.math.BigDecimal;
import java.util.List;

public record AcomodacaoRequest(
        String titulo,
        String descricao,
        BigDecimal precoDiaria,
        String enderecoCompleto,
        Integer maxHospedes,
        Integer quantidadeQuartos,
        Integer quantidadeBanheiros,
        Boolean aceitaPets,
        List<ComodidadeRequest> comodidades,
        TipoAcomodacoes tipoAcomodacao
) {
}
