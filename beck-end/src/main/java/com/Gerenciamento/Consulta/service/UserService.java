package com.Gerenciamento.Consulta.service;

import com.Gerenciamento.Consulta.dto.CreateUserDTO;
import com.Gerenciamento.Consulta.dto.UpdateUserDTO;
import com.Gerenciamento.Consulta.entities.User;
import com.Gerenciamento.Consulta.entities.UserRole;
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

    public User saveUser(CreateUserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getRole() != null) {
            user.setRole(userDTO.getRole());
        } else {
            user.setRole(UserRole.USER);
        }

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
