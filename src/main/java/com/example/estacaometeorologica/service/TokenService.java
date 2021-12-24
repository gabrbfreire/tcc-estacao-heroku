package com.example.estacaometeorologica.service;

import com.example.estacaometeorologica.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private String secret = "RK$ksT0nxKxGatTrvEwqKw/";

    public String gerarToken(Authentication authentication){
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + Long.parseLong("1800000"));

        return Jwts.builder()
                .setIssuer("API estação meteorológica")
                .setSubject("" + usuario.getId())
                .setIssuedAt(agora)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getIdUsuario(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
