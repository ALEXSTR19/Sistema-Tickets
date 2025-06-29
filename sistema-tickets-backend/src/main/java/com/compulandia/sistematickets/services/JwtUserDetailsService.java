package com.compulandia.sistematickets.services;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compulandia.sistematickets.entities.User;
import com.compulandia.sistematickets.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepo;

  @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  User user = userRepo.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

  List<GrantedAuthority> authorities = user.getRoles().stream()
      .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
      .collect(Collectors.toList());

  return new org.springframework.security.core.userdetails.User(
      user.getUsername(),
      user.getPassword(),
      authorities
  );
}


  public User save(User u) {
    u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
    return userRepo.save(u);
  }
}

