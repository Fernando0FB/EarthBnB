package com.unesc.earthBnb.controller;

import com.unesc.earthBnb.dto.request.ComodidadeRequest;
import com.unesc.earthBnb.dto.response.ComodidadeResponse;
import com.unesc.earthBnb.service.ComodidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comodidades")
public class ComodidadeController {

    private final ComodidadeService comodidadeService;

    public ComodidadeController(ComodidadeService comodidadeService) {
        this.comodidadeService = comodidadeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComodidadeResponse> getComodidadeById(@PathVariable Long id) {
        return ResponseEntity.ok(comodidadeService.getComodidadeById(id));
    }

    @GetMapping
    public ResponseEntity<List<ComodidadeResponse>> getAllComodidades() {
        return ResponseEntity.ok(comodidadeService.getAllComodidades());
    }

    @PostMapping
    public ResponseEntity<ComodidadeResponse> createComodidade(@Valid @RequestBody ComodidadeRequest comodidadeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comodidadeService.createComodidade(comodidadeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComodidadeResponse> updateComodidade(
            @PathVariable Long id,
            @Valid @RequestBody ComodidadeRequest comodidade
    ) {
        return ResponseEntity.ok(comodidadeService.updateComodidade(id, comodidade));
    }

    @DeleteMapping("/{id}")
    public void deleteComodidade(@RequestParam Long id) {comodidadeService.deleteComodidade(id);}

}
