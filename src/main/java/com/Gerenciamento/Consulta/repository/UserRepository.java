package com.Gerenciamento.Consulta.controller.repository;

import com.Gerenciamento.Consulta.controller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    UserDetails findByLogin(String login);
}
