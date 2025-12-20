package com.diego.cleanArch.application.usecase;

import com.diego.cleanArch.core.domain.exceptions.DomainException;
import com.diego.cleanArch.core.ports.UserRepository;

import java.util.UUID;

public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Input input){
        boolean deleted = userRepository.delete(input.id);
        if (!deleted){
            throw new DomainException("User not found");
        }
    }

    public record  Input(UUID id){}
}
