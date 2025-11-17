package com.unesc.earthBnb.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservaPutRequest(
        LocalDate dataCheckin,
        LocalDate dataCheckout
) {
}