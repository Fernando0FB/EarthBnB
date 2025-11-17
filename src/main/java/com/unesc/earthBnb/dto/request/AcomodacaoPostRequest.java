package com.unesc.earthBnb.dto.request;

import com.unesc.earthBnb.enums.TipoAcomodacoes;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record AcomodacaoPostRequest(
        @NotBlank(message = "Título é obrigatório")
        @Size(max = 120, message = "Título deve ter no máximo 120 caracteres")
        String titulo,

        String descricao,

        @NotNull(message = "Preço da diária é obrigatório")
        @DecimalMin(value = "0.01", message = "Preço da diária deve ser maior que zero")
        BigDecimal precoDiaria,

        @NotBlank(message = "Endereço completo é obrigatório")
        @Size(max = 255, message = "Endereço completo deve ter no máximo 255 caracteres")
        String enderecoCompleto,

        @NotNull(message = "Máximo de hóspedes é obrigatório")
        @Min(value = 1, message = "Deve aceitar pelo menos 1 hóspede")
        Integer maxHospedes,

        @NotNull(message = "Quantidade de quartos é obrigatória")
        @Min(value = 1, message = "Deve ter pelo menos 1 quarto")
        Integer quantidadeQuartos,

        @NotNull(message = "Quantidade de banheiros é obrigatória")
        @Min(value = 1, message = "Deve ter pelo menos 1 banheiro")
        Integer quantidadeBanheiros,

        @NotNull(message = "Informação sobre aceitar pets é obrigatória")
        Boolean aceitaPets,

        List<ComodidadeRequest> comodidades,

        @NotNull(message = "Tipo de acomodação é obrigatório")
        TipoAcomodacoes tipoAcomodacao
) {
}