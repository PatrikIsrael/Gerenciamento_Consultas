package com.Gerenciamento.Consulta.services.TestsUsers;

import com.Gerenciamento.Consulta.dto.UserDTO;
import com.Gerenciamento.Consulta.entities.UserRole;
import com.Gerenciamento.Consulta.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

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

}
