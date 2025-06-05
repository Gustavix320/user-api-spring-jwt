package com.gusta.userapi.controller;

import com.gusta.userapi.model.User;
import com.gusta.userapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserController(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // 🔐 Protegido por token JWT (apenas usuários autenticados)
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public List<User> list() {
        return repo.findAll();
    }

    // 🔓 Público (sem autenticação JWT) - Cadastro de novo usuário
    @PostMapping
    public User create(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword())); // Criptografar senha
        return repo.save(user);
    }

    // 🔐 Protegido
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    // 🔐 Protegido
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // 🔐 Protegido
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // 🔐 Protegido - apenas para teste após login
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "✅ Login realizado com sucesso! Bem-vindo à API.";
    }
}
