package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entity.User;
import com.Gerenciamento.Consulta.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(UserRole role);
    Optional<User> findByLogin(String login);
}
