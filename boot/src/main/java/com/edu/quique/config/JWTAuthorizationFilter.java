package com.edu.quique.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
  public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
  public static final String TOKEN_BEARER_PREFIX = "Bearer ";

  // JWT
  public static final String SUPER_SECRET_KEY =
      "ZnJhc2VzbGFyZ2FzcGFyYWNvbG9jYXJjb21vY2xhdmVlbnVucHJvamVjdG9kZWVtZXBsb3BhcmFqd3Rjb25zcHJpbmdzZWN1cml0eQ==bWlwcnVlYmFkZWVqbXBsb3BhcmFiYXNlNjQ=";

  private Claims setSigningKey(HttpServletRequest request) {
    String jwtToken = request.getHeader(HEADER_AUTHORIZACION_KEY).replace(TOKEN_BEARER_PREFIX, "");

    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey(SUPER_SECRET_KEY))
        .build()
        .parseClaimsJws(jwtToken)
        .getBody();
  }

  private void setAuthentication(Claims claims) {

    List<String> authorities = (List<String>) claims.get("authorities");

    UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(
            claims.getSubject(),
            null,
            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  private boolean isJWTValid(HttpServletRequest request, HttpServletResponse res) {
    String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
    return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_BEARER_PREFIX);
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      if (isJWTValid(request, response)) {
        Claims claims = setSigningKey(request);
        if (claims.get("authorities") != null) {
          setAuthentication(claims);
        } else {
          SecurityContextHolder.clearContext();
        }
      } else {
        SecurityContextHolder.clearContext();
      }
      filterChain.doFilter(request, response);
    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
      return;
    }
  }

  private Key getSigningKey(String secret) {
    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
