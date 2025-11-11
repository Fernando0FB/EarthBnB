package com.unesc.earthBnb.controller;

import com.unesc.earthBnb.config.TokenConfig;
import com.unesc.earthBnb.dto.LoginRequest;
import com.unesc.earthBnb.dto.LoginResponse;
import com.unesc.earthBnb.dto.UsuarioRequest;
import com.unesc.earthBnb.dto.UsuarioResponse;
import com.unesc.earthBnb.enums.Role;
import com.unesc.earthBnb.mapper.UsuarioMapper;
import com.unesc.earthBnb.model.Usuarios;
import com.unesc.earthBnb.repository.UsuarioRespository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRespository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UsuarioRespository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
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
    public ResponseEntity<UsuarioResponse> register(@Valid @RequestBody UsuarioRequest request) {
        Usuarios usuario = UsuarioMapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.senha()));

        if (request.role() != null) {
            usuario.setRoles(Set.of(request.role()));
        } else {
            usuario.setRoles(Set.of(Role.ROLE_USER));
        }

        usuario.setCriadoEm(LocalDate.now());
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toResponse(usuario));
    }


}
