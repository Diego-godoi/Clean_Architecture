package com.diego.cleanArch.application.usecase;

import com.diego.cleanArch.core.domain.User;
import com.diego.cleanArch.core.domain.exceptions.ResourceAlreadyExistsException;
import com.diego.cleanArch.core.domain.exceptions.ResourceNotFoundException;
import com.diego.cleanArch.core.ports.UserRepository;

import java.util.UUID;

public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Output execute(Input input) {
        User user = userRepository.findById(input.id())
                .orElseThrow(() -> new ResourceNotFoundException("User", input.id()));

        if (!user.getEmail().equals(input.email())) {
            userRepository.findByEmail(input.email()).ifPresent(existingUser -> {
                if (!existingUser.getId().equals(input.id())) {
                    throw new ResourceAlreadyExistsException("User", "Email", input.email());
                }
            });
        }

        user.changeName(input.name);
        user.changeEmail(input.email);
        user.changePassword(input.password);

        User saved = userRepository.save(user);

        return new Output(saved.getId(), saved.getName(), saved.getEmail());
    }

    public record Input(UUID id, String name, String email, String password) {
    }

    public record Output(UUID id, String name, String email) {
    }
}
