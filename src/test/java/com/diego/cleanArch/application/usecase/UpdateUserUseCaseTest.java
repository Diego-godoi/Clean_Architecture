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
import com.diego.cleanArch.core.domain.exceptions.ResourceNotFoundException;
import com.diego.cleanArch.core.ports.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserUseCase useCase;

    @Test
    void shouldUpdateUserSuccessfully() {
        UUID userId = UUID.randomUUID();
        User existingUser = new User(userId, "Old name", "oldpass", "old@test.com");

        UpdateUserUseCase.Input input = new UpdateUserUseCase.Input(userId, "Update user", "update@test.com",
                "123456");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(input.email())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UpdateUserUseCase.Output output = useCase.execute(input);

        assertNotNull(output);
        assertEquals(userId, output.id());
        assertEquals(input.name(), output.name());
        assertEquals(input.email(), output.email());

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).findByEmail(input.email());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        UUID userId = UUID.randomUUID();
        UpdateUserUseCase.Input input = new UpdateUserUseCase.Input(userId, "Old name", "old@test.com", "oldpass");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> useCase.execute(input));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).findByEmail(any());
        verify(userRepository, never()).save(any());
    }
}
