package com.diego.cleanArch.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.diego.cleanArch.core.domain.User;
import com.diego.cleanArch.core.ports.UserRepository;

@ExtendWith(MockitoExtension.class)
public class FindAllUserUseCaseTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private FindAllUserUseCase useCase;

    @Test
    void shouldReturnAllUsersSuccessfully() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        User user1 = new User(id1, "John Doe", "123456", "john@test.com");
        User user2 = new User(id2, "Jane Doe", "123456", "jane@test.com");

        when(userRepository.findAll())
                .thenReturn(List.of(user1, user2));

        // Act
        List<FindAllUserUseCase.Output> output = useCase.execute();

        // Assert
        assertNotNull(output);
        assertEquals(2, output.size());

        FindAllUserUseCase.Output first = output.get(0);
        assertEquals(id1, first.id());
        assertEquals("John Doe", first.name());
        assertEquals("john@test.com", first.email());

        FindAllUserUseCase.Output second = output.get(1);
        assertEquals(id2, second.id());
        assertEquals("Jane Doe", second.name());
        assertEquals("jane@test.com", second.email());

        // Verify
        verify(userRepository, times(1)).findAll();
    }
}
