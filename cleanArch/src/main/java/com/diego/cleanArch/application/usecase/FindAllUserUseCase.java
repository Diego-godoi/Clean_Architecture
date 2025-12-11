package com.diego.cleanArch.application.usecase;

import com.diego.cleanArch.core.domain.User;
import com.diego.cleanArch.core.ports.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FindAllUserUseCase {
    private final UserRepository userRepository;

    public FindAllUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Output> execute(){
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> new Output(u.getId(), u.getName(), u.getEmail())).collect(Collectors.toList());
    }

    public record Output(UUID id, String name, String email){}
}
