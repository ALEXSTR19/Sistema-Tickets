package com.compulandia.sistematickets.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.compulandia.sistematickets.services.JwtTokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  @Autowired JwtTokenUtil jwtUtil;
  @Autowired UserDetailsService uds;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain c) throws IOException, ServletException {
    String h = req.getHeader("Authorization");
    if (h!=null && h.startsWith("Bearer ")) {
      String token = h.substring(7);
      String u = jwtUtil.getUsernameFromToken(token);
      if (u!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
        UserDetails ud = uds.loadUserByUsername(u);
        if (jwtUtil.validateToken(token, ud)) {
          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities());
          auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      }
    }
    c.doFilter(req, res);
  }
}

