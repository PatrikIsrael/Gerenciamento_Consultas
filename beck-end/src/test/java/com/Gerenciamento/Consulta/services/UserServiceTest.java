package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.dto.UserDTO;
import com.Gerenciamento.Consulta.entities.User;
import com.Gerenciamento.Consulta.entities.UserRole;
import com.Gerenciamento.Consulta.repository.UserRepository;
import com.Gerenciamento.Consulta.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser_Success() {
        UserDTO userDTO = new UserDTO(null, "testUser", "testPassword", "test@example.com", UserRole.USER);

        UserDTO savedUser = userService.createUser(userDTO);

        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getLogin());
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("testPassword", savedUser.getPassword());
        assertEquals(UserRole.USER, savedUser.getRole());
    }

    @Test
    public void testFindUserById_Success() {
        User user = new User(1, "testUser", "password", "test@test.com", UserRole.USER);
        userRepository.save(user);

        UserDTO foundUser = userService.findUserById(user.getId());

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
        assertEquals("testUser", foundUser.getLogin());
        assertEquals("test@test.com", foundUser.getEmail());
    }

    @Test
    public void testFindUserById_NotFound() {
        UserDTO foundUser = userService.findUserById(999L);

        assertNull(foundUser);
    }

    @Test
    public void testFindAllUsers() {
        userRepository.save(new User(1, "user1", "password1", "user1@test.com", UserRole.USER));
        userRepository.save(new User(2, "user2", "password2", "user2@test.com", UserRole.USER));

        List<UserDTO> users = userService.findAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testUpdateUser_Success() {
        User user = new User(1, "originalUser", "password", "original@test.com", UserRole.USER);
        userRepository.save(user);

        UserDTO updateDetails = new UserDTO(user.getId(), "updatedUser", "updated@test.com", null, UserRole.USER);

        UserDTO updatedUser = userService.updateUser(user.getId(), updateDetails);

        assertNotNull(updatedUser);
        assertEquals("updatedUser", updatedUser.getLogin());
        assertEquals("updated@test.com", updatedUser.getEmail());
    }

    @Test
    public void testUpdateUser_NotFound() {
        UserDTO updateDetails = new UserDTO(999L, "nonExistentUser", "nonexistent@test.com", null, UserRole.USER);

        UserDTO updatedUser = userService.updateUser(999L, updateDetails);

        assertNull(updatedUser);
    }

    @Test
    public void testDeleteUser_Success() {
        User user = new User(1, "testUser", "password", "test@test.com", UserRole.USER);
        userRepository.save(user);

        boolean isDeleted = userService.deleteUser(user.getId());

        assertTrue(isDeleted);
        assertNull(userRepository.findById(user.getId()).orElse(null));
    }

    @Test
    public void testDeleteUser_NotFound() {
        boolean isDeleted = userService.deleteUser(999L);
        assertFalse(isDeleted);
    }

    @Test
    public void testCreateUser_InvalidData() {
        UserDTO invalidUser = new UserDTO(null, "", "invalidemail", null, UserRole.USER);

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(invalidUser));
    }
}
