package com.example.imagecrudapi.demo.Service;

import com.example.imagecrudapi.demo.Model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.expiration.time}")
    private String expiration;

    @Value("${api.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .setIssuer("API Images")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean isTokenValido(String token) {

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        try{
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Long getIdUsuario(String token) {

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
}
