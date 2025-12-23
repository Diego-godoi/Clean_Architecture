package com.diego.cleanArch.application.usecase;

import com.diego.cleanArch.core.domain.User;
import com.diego.cleanArch.core.domain.exceptions.ResourceAlreadyExistsException;
import com.diego.cleanArch.core.ports.UserRepository;

import java.util.UUID;

public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Output execute(Input input) {
        if (userRepository.findByEmail(input.email).isPresent()) {
            throw new ResourceAlreadyExistsException("User", "Email", input.email);
        }

        User user = new User(null, input.name, input.password, input.email);
        User saved = userRepository.save(user);

        return new Output(saved.getId(), saved.getName(), saved.getEmail());
    }

    public record Input(String name, String email, String password) {
    }

    public record Output(UUID id, String name, String email) {
    }
}
