package com.gusta.userapi.controller;

import com.gusta.userapi.config.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "auth-controller", description = "Autenticação e geração de token JWT")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "Autentica o usuário e retorna um token JWT válido")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String password = request.get("password");

            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            UserDetails user = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(user.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;

        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
}
