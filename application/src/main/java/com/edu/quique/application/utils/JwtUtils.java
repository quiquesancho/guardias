package com.edu.quique.application.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class JwtUtils {
  private static final String SECRET_KEY = "0:*5X-!YD!PEe.DVg-j.ctrrPCqf8-,%bMw[z?QN4RRNJ$rZ1S";
  private static final Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

  public static String generateToken(
      String email, String name, String firstSurname, String secondSurname, List<String> roles) {
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
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact();
  }

  public static boolean validateToken(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parserBuilder()
              .setSigningKey(secretKey)
              .build()
              .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage());
      return false;
    }
  }
}
