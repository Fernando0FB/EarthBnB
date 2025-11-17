package com.unesc.earthBnb.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservaResponse(
        Long id,
        UsuarioResponse usuarioResponse,
        AcomodacaoResponse acomodacaoResponse,
        LocalDate dataCheckin,
        LocalDate dataCheckout,
        Integer quantidadeDiarias,
        BigDecimal valorPagamento,
        LocalDateTime dataReserva
) {
}
