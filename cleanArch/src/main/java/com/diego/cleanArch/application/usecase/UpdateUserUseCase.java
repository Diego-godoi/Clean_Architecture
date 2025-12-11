package com.diego.cleanArch.application.usecase;

import com.diego.cleanArch.core.domain.User;
import com.diego.cleanArch.core.domain.exceptions.DomainException;
import com.diego.cleanArch.core.ports.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Output execute(Input input){
        User user = userRepository.findById(input.id())
                .orElseThrow(() -> new DomainException("User not found"));

        user.setName(input.name);
        user.setEmail(input.email);
        user.setPassword(input.password);

        User saved = userRepository.save(user);

        return new Output(saved.getId(), saved.getName(), saved.getEmail());
    }

    public record Input(UUID id, String name, String email, String password){}
    public record Output(UUID id, String name, String email){}
}
