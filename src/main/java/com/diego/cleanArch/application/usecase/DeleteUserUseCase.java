package com.diego.cleanArch.application.usecase;

import com.diego.cleanArch.core.domain.exceptions.ResourceNotFoundException;
import com.diego.cleanArch.core.ports.UserRepository;

import java.util.UUID;

public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Input input) {
        boolean deleted = userRepository.delete(input.id);
        if (!deleted) {
            throw new ResourceNotFoundException("User", input.id());
        }
    }

    public record Input(UUID id) {
    }
}
