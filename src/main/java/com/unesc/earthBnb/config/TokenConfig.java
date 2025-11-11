package com.unesc.earthBnb.config;


import com.auth0.jwt.algorithms.Algorithm;
import com.unesc.earthBnb.model.Usuarios;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    private String secret = "secret";

    public String generateToken(Usuarios usuario) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userId", usuario.getId())
                .withClaim("roles", usuario.getRoles().stream().map(Enum::name).toList())
                .withSubject(usuario.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)
                    .build().verify(token);

            return Optional.of(JWTUserData.builder()
                    .userId(decode.getClaim("userId").asLong())
                    .email(decode.getSubject())
                    .roles(decode.getClaim("roles").asList(String.class))
                    .build());
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}










