package com.gusta.userapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                 "/",
                   "/h2-console/**",
                  "/swagger-ui/**",
                  "/v3/api-docs/**",
                   "/swagger-ui.html",
                    "/swagger-ui/index.html"
             ).permitAll()
          .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
          .requestMatchers(HttpMethod.POST, "/users").permitAll()
          .anyRequest().authenticated()
)

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // ✅ filtro JWT

        return http.build(); // 🔒 Este fechamento estava faltando!
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
