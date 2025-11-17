package com.unesc.earthBnb.controller;

import com.unesc.earthBnb.dto.request.AcomodacaoPostRequest;
import com.unesc.earthBnb.dto.request.AcomodacaoPutRequest;
import com.unesc.earthBnb.dto.response.AcomodacaoResponse;
import com.unesc.earthBnb.service.AcomodacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acomodacoes")
public class AcomodacaoController {

    private final AcomodacaoService acomodacaoService;

    public AcomodacaoController(AcomodacaoService acomodacaoService) {
        this.acomodacaoService = acomodacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcomodacaoResponse> getAcomodacaoById(@PathVariable Long id) {
        return ResponseEntity.ok(acomodacaoService.getAcomodacaoById(id));
    }

    @GetMapping
    public ResponseEntity<List<AcomodacaoResponse>> getAllAcomodacoes() {
        return ResponseEntity.ok(acomodacaoService.getAllAcomodacoes());
    }

    @PostMapping
    public ResponseEntity<AcomodacaoResponse> createAcomodacao(@Valid @RequestBody AcomodacaoPostRequest acomodacaoPostRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(acomodacaoService.createAcomodacao(acomodacaoPostRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcomodacaoResponse> updateAcomodacao(
            @PathVariable Long id,
            @Valid @RequestBody AcomodacaoPutRequest acomodacaoPutRequest
    ) {
        return ResponseEntity.ok(acomodacaoService.updateAcomodacao(id, acomodacaoPutRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcomodacao(@PathVariable Long id) {
        acomodacaoService.deleteAcomodacao(id);
        return ResponseEntity.noContent().build();
    }

}
