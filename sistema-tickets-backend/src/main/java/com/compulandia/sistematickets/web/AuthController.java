package com.compulandia.sistematickets.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.compulandia.sistematickets.entities.User;
import com.compulandia.sistematickets.services.JwtTokenUtil;
import com.compulandia.sistematickets.services.JwtUserDetailsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AuthController {
  @Autowired AuthenticationManager am;
  @Autowired JwtTokenUtil jwtUtil;
  @Autowired JwtUserDetailsService uds;
  @Autowired PasswordEncoder encoder;
  @Autowired com.compulandia.sistematickets.repository.UserRepository repo;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User u) {
    u.setPassword(encoder.encode(u.getPassword()));
    repo.save(u);
    return ResponseEntity.ok("registrado");
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> auth(@RequestBody com.compulandia.sistematickets.config.AuthRequest r) throws Exception {
    am.authenticate(new UsernamePasswordAuthenticationToken(r.getUsername(),r.getPassword()));
    final UserDetails ud = uds.loadUserByUsername(r.getUsername());
    final String token = jwtUtil.generateToken(ud);
    return ResponseEntity.ok(new com.compulandia.sistematickets.config.AuthResponse(token));
  }
}

