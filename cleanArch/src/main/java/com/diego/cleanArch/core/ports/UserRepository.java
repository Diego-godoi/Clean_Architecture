package com.diego.cleanArch.core.ports;

import com.diego.cleanArch.core.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    boolean delete(UUID id);
    List<User> findAll();
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
}
