package com.unesc.earthBnb.service;

import com.unesc.earthBnb.dto.request.UsuarioPostRequest;
import com.unesc.earthBnb.dto.request.UsuarioPutRequest;
import com.unesc.earthBnb.dto.response.UsuarioResponse;
import com.unesc.earthBnb.enums.Role;
import com.unesc.earthBnb.exception.UsuarioNaoEncontradoException;
import com.unesc.earthBnb.mapper.UsuarioMapper;
import com.unesc.earthBnb.model.Usuarios;
import com.unesc.earthBnb.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuariosRespository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuariosRespository;
    }

    @Transactional(readOnly = true)
    public UsuarioResponse getUsuarioById(Long id) {
        Usuarios usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        return UsuarioMapper.toResponse(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }

    @Transactional
    public UsuarioResponse createUsuario(UsuarioPostRequest request) {
        Usuarios usuario = UsuarioMapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.senha()));

        if (request.role() != null) {
            usuario.setRoles(Set.of(request.role()));
        } else {
            usuario.setRoles(Set.of(Role.ROLE_USER));
        }

        usuario.setCriadoEm(LocalDate.now());
        usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(usuario);
    }

    @Transactional
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNaoEncontradoException(id);
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioResponse updateUsuario(Long id, UsuarioPutRequest usuarioUpdate) {
        Usuarios usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        UsuarioMapper.merge(usuario, usuarioUpdate);
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(usuario);
    }
}
