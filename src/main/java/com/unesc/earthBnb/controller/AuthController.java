package com.unesc.earthBnb.controller;

import com.unesc.earthBnb.config.TokenConfig;
import com.unesc.earthBnb.dto.request.LoginRequest;
import com.unesc.earthBnb.dto.response.LoginResponse;
import com.unesc.earthBnb.dto.request.UsuarioPostRequest;
import com.unesc.earthBnb.dto.response.UsuarioResponse;
import com.unesc.earthBnb.model.Usuarios;
import com.unesc.earthBnb.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, TokenConfig tokenConfig, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.usuario(), loginRequest.senha());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        String token = tokenConfig.generateToken(usuario);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(@Valid @RequestBody UsuarioPostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuario(request));
    }
}
