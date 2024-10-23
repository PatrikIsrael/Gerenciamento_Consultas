package com.Gerenciamento.Consulta.controller.service;

import com.Gerenciamento.Consulta.controller.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Gerenciamento.Consulta.controller.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado com o username: " + username);
        }

        return user.get();
    }
}

