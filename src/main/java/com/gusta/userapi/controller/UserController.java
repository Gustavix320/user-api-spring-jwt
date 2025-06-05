package com.gusta.userapi.controller;

import com.gusta.userapi.model.User;
import com.gusta.userapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth") 
public class UserController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserController(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // Listar todos os usuários
    @GetMapping
    public List<User> list() {
        return repo.findAll();
    }

    // Cadastrar novo usuário
    @PostMapping
    public User create(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword())); // Criptografar senha
        return repo.save(user);
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword())); // Criptografar senha
        return repo.save(user);
    }

    // Remover usuário
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // Página inicial após login
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "✅ Login realizado com sucesso! Bem-vindo à API.";
    }
}
