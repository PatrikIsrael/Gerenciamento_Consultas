package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.entity.User;
import com.Gerenciamento.Consulta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
   private UserRepository userRepository;

    public List<User>findAllUsers (){
        return userRepository.findAll();
    }

    public User findById (Long Id){
        return userRepository.findById(Id).orElse(null);
    }

    public User saveUser (User user){
        return userRepository.save(user);
    }

    public User UpdateUser (Long Id, User user){
        user.setId(Id);
        return userRepository.save(user);
    }

    public void deleteUser(Long Id){
        userRepository.deleteById(Id);
    }

}
