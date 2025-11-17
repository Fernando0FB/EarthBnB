package com.unesc.earthBnb.controller;

import com.unesc.earthBnb.dto.request.ReservaCreateRequest;
import com.unesc.earthBnb.dto.request.ReservaPutRequest;
import com.unesc.earthBnb.dto.response.ReservaResponse;
import com.unesc.earthBnb.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponse> getReservaById(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.getReservaById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponse>> getAllReservas() {
        return ResponseEntity.ok(reservaService.getAllReservas());
    }

    @PostMapping
    public ResponseEntity<ReservaResponse> createReserva(@Valid @RequestBody ReservaCreateRequest reservaCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaService.createReserva(reservaCreateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponse> updateReserva(
            @PathVariable Long id,
            @Valid @RequestBody ReservaPutRequest reservaPutRequest
    ) {
        return ResponseEntity.ok(reservaService.updateReserva(id, reservaPutRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

}
