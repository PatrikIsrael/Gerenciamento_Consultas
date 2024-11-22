package com.Gerenciamento.Consulta.services;

import com.Gerenciamento.Consulta.dto.PatientDTO;
import com.Gerenciamento.Consulta.entity.Patient;
import com.Gerenciamento.Consulta.entity.User;
import com.Gerenciamento.Consulta.entity.UserRole;
import com.Gerenciamento.Consulta.repository.UserRepository;
import com.Gerenciamento.Consulta.service.PatientService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreatePatient_Success() {

        User user = new User(1,"testUser", "password","teste@teste.com", UserRole.USER);
        userRepository.save(user);


        PatientDTO patientDTO = new PatientDTO(
                user.getId(),
                "John Doe",
                "1990-01-01",
                "123456789",
                "Histórico médico"
        );


        Patient savedPatient = patientService.savePatient(patientDTO);


        assertNotNull(savedPatient);
        assertEquals("John Doe", savedPatient.getName());
        assertEquals("1990-01-01", savedPatient.getDateOfBirth());
        assertEquals("123456789", savedPatient.getPhoneNumber());
        assertEquals("Histórico médico", savedPatient.getMedicalHistory());
    }
}

