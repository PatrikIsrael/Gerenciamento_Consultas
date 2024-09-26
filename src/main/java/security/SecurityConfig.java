package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desabilita CSRF para simplificar a configuração, considere habilitar para produção
                .authorizeRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Permite acesso apenas para ADMIN
                        .requestMatchers("/doctor/**").hasRole("DOCTOR") // Permite acesso apenas para DOCTOR
                        .requestMatchers("/patient/**").hasRole("PATIENT") // Permite acesso apenas para PATIENT
                        .requestMatchers("/public/**", "/login", "/register").permitAll() // Permite acesso a páginas públicas
                        .anyRequest().authenticated() // Qualquer outra requisição deve estar autenticada
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada
                        .permitAll() // Permite acesso à página de login para todos
                )
                .logout(logout -> logout
                        .permitAll() // Permite logout para todos
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/403") // Página de acesso negado personalizada
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificador de senha
    }
}
