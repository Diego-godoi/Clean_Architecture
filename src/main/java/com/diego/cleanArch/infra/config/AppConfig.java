package com.diego.cleanArch.infra.config;

import com.diego.cleanArch.application.usecase.CreateUserUseCase;
import com.diego.cleanArch.application.usecase.DeleteUserUseCase;
import com.diego.cleanArch.application.usecase.FindAllUserUseCase;
import com.diego.cleanArch.application.usecase.UpdateUserUseCase;
import com.diego.cleanArch.core.ports.UserRepository;
import com.diego.cleanArch.infra.persistence.repository.UserRepositoryImpl;
import com.diego.cleanArch.infra.persistence.spring.SpringDataUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserRepository userRepository(SpringDataUserRepository springRepo) {
        return new UserRepositoryImpl(springRepo);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository repo) {
        return new CreateUserUseCase(repo);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(UserRepository repo) {
        return new UpdateUserUseCase(repo);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserRepository repo) {
        return new DeleteUserUseCase(repo);
    }

    @Bean
    public FindAllUserUseCase findAllUserUseCase(UserRepository repo) {
        return new FindAllUserUseCase(repo);
    }
}
