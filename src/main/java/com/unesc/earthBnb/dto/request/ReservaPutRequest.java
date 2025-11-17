package com.unesc.earthBnb.dto.request;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservaPutRequest(
        LocalDate dataCheckin,
        LocalDate dataCheckout,
        @Positive(message = "A quantidade de diárias deve ser maior que zero")
        Integer quantidadeDiarias,
        @Positive(message = "O valor do pagamento deve ser maior que zero")
        BigDecimal valorPagamento,
        LocalDateTime dataReserva
) {
}