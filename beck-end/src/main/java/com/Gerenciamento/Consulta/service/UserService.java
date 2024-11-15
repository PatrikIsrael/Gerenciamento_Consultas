package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.dto.CreateUserDTO;
import com.Gerenciamento.Consulta.entity.User;
import com.Gerenciamento.Consulta.dto.UpdateUserDTO;
import com.Gerenciamento.Consulta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setLogin(createUserDTO.getLogin());
        user.setPassword(createUserDTO.getPassword());
        user.setEmail(createUserDTO.getEmail());


        return userRepository.save(user);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(Long id, UpdateUserDTO updateUserDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setPassword(updateUserDTO.getPassword());
            user.setEmail(updateUserDTO.getEmail());
            user.setRole(updateUserDTO.getRole());
            return userRepository.save(user);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
