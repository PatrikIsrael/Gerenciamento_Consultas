package com.Gerenciamento.Consulta.controller.service;

import com.Gerenciamento.Consulta.controller.entity.User;
import com.Gerenciamento.Consulta.controller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User save(User user) {
        return userRepository.save(user);
    }


    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
