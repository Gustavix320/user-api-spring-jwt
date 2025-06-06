package com.gusta.userapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/")
    public String home() {
        return "✅ Login realizado com sucesso! Bem-vindo à API.";
    }
}
