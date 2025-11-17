package com.unesc.earthBnb.dto.request;

import com.unesc.earthBnb.enums.TipoAcomodacoes;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record AcomodacaoPutRequest(
        @Size(max = 120, message = "Título deve ter no máximo 120 caracteres")
        String titulo,
        String descricao,
        @DecimalMin(value = "0.01", message = "Preço da diária deve ser maior que zero")
        BigDecimal precoDiaria,
        @Size(max = 255, message = "Endereço completo deve ter no máximo 255 caracteres")
        String enderecoCompleto,
        @Min(value = 1, message = "Deve aceitar pelo menos 1 hóspede")
        Integer maxHospedes,
        @Min(value = 1, message = "Deve ter pelo menos 1 quarto")
        Integer quantidadeQuartos,
        @Min(value = 1, message = "Deve ter pelo menos 1 banheiro")
        Integer quantidadeBanheiros,
        Boolean aceitaPets,
        List<ComodidadeRequest> comodidades,
        TipoAcomodacoes tipoAcomodacao
) {
}