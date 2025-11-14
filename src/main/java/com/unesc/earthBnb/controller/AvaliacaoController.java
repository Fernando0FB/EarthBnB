package com.unesc.earthBnb.controller;

import com.unesc.earthBnb.dto.request.AvaliacaoRequest;
import com.unesc.earthBnb.dto.response.AvaliacaoResponse;
import com.unesc.earthBnb.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse> getAvaliacaoById(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.getAvaliacaoById(id));
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoResponse>> getAllAvaliacoes() {
        return ResponseEntity.ok(avaliacaoService.getAllAvaliacoes());
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponse> createAvaliacao(@Valid @RequestBody AvaliacaoRequest avaliacaoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(avaliacaoService.createAvaliacoes(avaliacaoRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse> updateAvaliacoes(
            @PathVariable Long id,
            @RequestBody AvaliacaoRequest avaliacaoRequest
    ) {
        return ResponseEntity.ok(avaliacaoService.updateAvaliacoes(id, avaliacaoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacoes(@RequestParam Long id) {
        avaliacaoService.deleteAvaliacoes(id);
        return ResponseEntity.noContent().build();
    }

}
