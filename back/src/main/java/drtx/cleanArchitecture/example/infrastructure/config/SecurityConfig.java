package drtx.cleanArchitecture.example.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // Seguridad para API: permite todo, sin login ni redirecciones
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF (innecesario para APIs REST)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permitir cualquier ruta sin autenticación
                )
                .cors(cors -> {}); // Habilita CORS (ver siguiente parte)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
