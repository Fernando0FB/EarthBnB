package com.unesc.earthBnb.controller;

import com.unesc.earthBnb.dto.request.UsuarioRequest;
import com.unesc.earthBnb.dto.response.UsuarioResponse;
import com.unesc.earthBnb.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuariosService;

    public UsuarioController(UsuarioService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable Long id) {
        return ResponseEntity.ok(usuariosService.getUsuarioById(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getAllUsuarios() {
        return ResponseEntity.ok(usuariosService.getAllUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> updateUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest usuario
    ) {
        return ResponseEntity.ok(usuariosService.updateUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@RequestParam Long id) {
        usuariosService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
