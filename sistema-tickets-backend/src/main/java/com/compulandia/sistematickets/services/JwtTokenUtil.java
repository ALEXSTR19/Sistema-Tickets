package com.compulandia.sistematickets.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs; {

  }

  public String generateToken(UserDetails u) {
    return null;
  }         // usa jjwt builder

  public String getUsernameFromToken(String t) {
    return null;
  }

  public boolean validateToken(String t, UserDetails u) {
    return false;
  }
}

