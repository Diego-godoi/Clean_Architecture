package com.diego.cleanArch.infra.persistence.repository;

import com.diego.cleanArch.core.domain.User;
import com.diego.cleanArch.core.ports.UserRepository;
import com.diego.cleanArch.infra.persistence.entity.UserJpaEntity;
import com.diego.cleanArch.infra.persistence.spring.SpringDataUserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Primary
public class UserRepositoryImpl implements UserRepository {
    private final SpringDataUserRepository springRepo;

    public UserRepositoryImpl(SpringDataUserRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = new UserJpaEntity(user);
        UserJpaEntity saved = springRepo.save(entity);
        return saved.toDomain();
    }

    @Override
    public boolean delete(UUID id) {
       if ( !springRepo.existsById(id)){
          return false;
       }
       springRepo.deleteById(id);
       return true;
    }

    @Override
    public List<User> findAll() {
        return springRepo.findAll().stream()
                .map(UserJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return springRepo.findById(id).map(UserJpaEntity::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springRepo.findByEmail(email).map(UserJpaEntity::toDomain);
    }
}
