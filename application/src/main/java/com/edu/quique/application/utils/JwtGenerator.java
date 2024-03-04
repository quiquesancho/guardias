package com.edu.quique.application.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtGenerator {
  public static String generateToken(
      String email, String name, String firstSurname, String secondSurname, List<String> roles)
      throws NoSuchAlgorithmException {
    Map<String, Object> claims = new HashMap<>();
    claims.put("email", email);
    claims.put("name", name);
    claims.put("firstSurname", firstSurname);
    claims.put("secondSurname", secondSurname);
    claims.put("role", roles);

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .signWith(getSecretKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private static Key getSecretKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
    return keyGenerator.generateKey();
  }
}
