package com.compulandia.sistematickets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compulandia.sistematickets.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);
}