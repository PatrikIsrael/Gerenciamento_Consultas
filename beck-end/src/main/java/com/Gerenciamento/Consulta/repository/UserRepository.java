package com.Gerenciamento.Consulta.repository;

import com.Gerenciamento.Consulta.entity.User;
import com.Gerenciamento.Consulta.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
    Optional<User>findByLogin(String login);
    List<User> findByUserRole(UserRole Role);

}
