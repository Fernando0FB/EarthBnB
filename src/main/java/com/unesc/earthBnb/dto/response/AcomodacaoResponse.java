package com.unesc.earthBnb.dto.response;

import com.unesc.earthBnb.enums.TipoAcomodacoes;
import java.math.BigDecimal;
import java.util.List;

public record AcomodacaoResponse(
        Long id,
        String titulo,
        String descricao,
        BigDecimal precoDiaria,
        String enderecoCompleto,
        Integer maxHospedes,
        Integer quantidadeQuartos,
        Integer quantidadeBanheiros,
        Boolean aceitaPets,
        List<ComodidadeResponse> comodidades,
        TipoAcomodacoes tipoAcomodacao
) {}