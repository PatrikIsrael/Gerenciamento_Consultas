package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entity.User;
import com.Gerenciamento.Consulta.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {
    Optional<User>findByLogin(String login);

    List<User> findBy (UserRole Role);

}
