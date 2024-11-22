package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.dto.PatientDTO;
import com.Gerenciamento.Consulta.entities.User;
import com.Gerenciamento.Consulta.entities.UserRole;
import com.Gerenciamento.Consulta.repository.UserRepository;
import com.Gerenciamento.Consulta.service.PatientService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSavePatient_Success() {


        User user = new User();
        user.setLogin("testUser");
        user.setPassword("password");
        user.setEmail("teste@teste.com");
        user.setRole(UserRole.USER);
        User savedUser = userRepository.save(user);


        PatientDTO patientDTO = new PatientDTO(
                savedUser.getId(),
                "John Doe",
                "98765412365",
                "1990-01-01",
                "123456789",
                "Histórico médico"
        );


        PatientDTO savedPatient = patientService.savePatient(patientDTO);


        assertNotNull(savedPatient, "O paciente salvo não pode ser nulo.");
        assertEquals("John Doe", savedPatient.getName(), "O nome do paciente não corresponde.");
        assertEquals("1990-01-01", savedPatient.getDateOfBirth(), "A data de nascimento não corresponde.");
        assertEquals("123456789", savedPatient.getPhoneNumber(), "O telefone não corresponde.");
        assertEquals("Histórico médico", savedPatient.getMedicalHistory(), "O histórico médico não corresponde.");
    }
}
