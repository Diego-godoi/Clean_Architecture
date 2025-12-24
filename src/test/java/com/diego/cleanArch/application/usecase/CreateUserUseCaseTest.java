package com.diego.cleanArch.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.diego.cleanArch.core.domain.User;
import com.diego.cleanArch.core.domain.exceptions.ResourceAlreadyExistsException;
import com.diego.cleanArch.core.ports.UserRepository;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCase useCase;

    @Test
    void shouldCreateUserSuccessfullyWhenEmailIsUnique() {
        // Arrange
        String name = "John Doe";
        String email = "john@example.com";
        String password = "password123";
        UUID expectedId = UUID.randomUUID();

        CreateUserUseCase.Input input = new CreateUserUseCase.Input(name, email, password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return new User(expectedId, user.getName(), user.getPassword(), user.getEmail());
        });

        // Act
        CreateUserUseCase.Output output = useCase.execute(input);

        // Assert
        assertNotNull(output);
        assertEquals(expectedId, output.id());
        assertEquals(name, output.name());
        assertEquals(email, output.email());

        // Verificar interações
        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailNotUnique() {
        String name = "John Doe";
        String email = "john@example.com";
        String password = "password123";

        CreateUserUseCase.Input input = new CreateUserUseCase.Input(name, email, password);

        User mockUser = new User(UUID.randomUUID(), "Existing user", "123456", email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        assertThrows(ResourceAlreadyExistsException.class, () -> useCase.execute(input));

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, never()).save(any());
    }
}
