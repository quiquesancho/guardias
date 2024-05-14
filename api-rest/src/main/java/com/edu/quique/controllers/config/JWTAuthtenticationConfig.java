package com.edu.quique.controllers.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;


@Configuration
public class JWTAuthtenticationConfig {
  public static final String SUPER_SECRET_KEY =
          "ZnJhc2VzbGFyZ2FzcGFyYWNvbG9jYXJjb21vY2xhdmVlbnVucHJvamVjdG9kZWVtZXBsb3BhcmFqd3Rjb25zcHJpbmdzZWN1cml0eQ==bWlwcnVlYmFkZWVqbXBsb3BhcmFiYXNlNjQ=";
  public static final long TOKEN_EXPIRATION_TIME = 1_800_000;

  public String getJWTToken(String username, List<String> authorities) {
    String token =
        Jwts.builder()
            .setId("")
            .setSubject(username)
            .claim("authorities", authorities)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
            .signWith(getSigningKey(SUPER_SECRET_KEY), SignatureAlgorithm.HS512)
            .compact();

    return "Bearer " + token;
  }

  public static Key getSigningKey(String secret) {
    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
