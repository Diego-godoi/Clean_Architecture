package com.diego.cleanArch.application.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.diego.cleanArch.core.domain.exceptions.ResourceNotFoundException;
import com.diego.cleanArch.core.ports.UserRepository;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCase useCase;

    @Test
    void shouldDeleteUserSuccessfully() {
        UUID userId = UUID.randomUUID();
        DeleteUserUseCase.Input input = new DeleteUserUseCase.Input(userId);

        when(userRepository.delete(input.id())).thenReturn(true);

        assertDoesNotThrow(() -> useCase.execute(input));

        verify(userRepository, times(1)).delete(userId);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        UUID userId = UUID.randomUUID();
        DeleteUserUseCase.Input input = new DeleteUserUseCase.Input(userId);

        when(userRepository.delete(input.id())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> useCase.execute(input));

        verify(userRepository, times(1)).delete(userId);
    }
}
