package com.gusta.userapi.controller;

import com.gusta.userapi.model.User;
import com.gusta.userapi.model.UserRequestDTO;
import com.gusta.userapi.model.UserResponseDTO;
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

    // 🔓 Público - Criar novo usuário
    @PostMapping
    public UserResponseDTO create(@RequestBody UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPassword(encoder.encode(dto.getPassword()));

        User saved = repo.save(user);
        return new UserResponseDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getRole());
    }

    // 🔐 Protegido - Listar todos
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public List<UserResponseDTO> list() {
        return repo.findAll().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole()))
                .toList();
    }

    // 🔐 Protegido - Buscar por ID
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public UserResponseDTO get(@PathVariable Long id) {
        return repo.findById(id)
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole()))
                .orElse(null);
    }

    // 🔐 Protegido - Atualizar usuário
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        User user = new User();
        user.setId(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPassword(encoder.encode(dto.getPassword()));

        User updated = repo.save(user);
        return new UserResponseDTO(updated.getId(), updated.getName(), updated.getEmail(), updated.getRole());
    }

    // 🔐 Protegido - Deletar usuário
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

}
