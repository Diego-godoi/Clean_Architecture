package com.diego.cleanArch.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.diego.cleanArch.core.domain.exceptions.InvalidDomainDataException;

public class UserTest {

    @Test
    void shouldCreateUserWithValidData() {
        UUID id = UUID.randomUUID();
        String name = "John Joe";
        String password = "123456";
        String email = "john@example.com";

        User user = new User(id, name, password, email);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    void shouldCreateUserWithNullId() {
        UUID id = null;
        String name = "John Joe";
        String password = "123456";
        String email = "john@example.com";

        User user = new User(id, name, password, email);

        assertNull(user.getId());
    }

    @Test
    void shouldThrowExceptionWhenNameIsInvalid() {
        UUID id = null;
        String name = "";
        String password = "123456";
        String email = "john@example.com";

        InvalidDomainDataException exception = assertThrows(InvalidDomainDataException.class,
                () -> new User(id, name, password, email));

        assertEquals("Name cannot be empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsTooShort() {
        UUID id = null;
        String name = "John Joe";
        String password = "123";
        String email = "john@example.com";

        InvalidDomainDataException exception = assertThrows(InvalidDomainDataException.class,
                () -> new User(id, name, password, email));

        assertEquals("Password must have at least 6 characters", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsEmpty() {
        UUID id = null;
        String name = "John Joe";
        String password = "";
        String email = "john@example.com";

        InvalidDomainDataException exception = assertThrows(InvalidDomainDataException.class,
                () -> new User(id, name, password, email));

        assertEquals("Password must have at least 6 characters", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        UUID id = null;
        String name = "John Joe";
        String password = "pass12345";
        String email = "";

        InvalidDomainDataException exception = assertThrows(InvalidDomainDataException.class,
                () -> new User(id, name, password, email));

        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        UUID id = null;
        String name = "John Joe";
        String password = "pass12345";
        String email = "johnexamplecom"; // without @ and .

        InvalidDomainDataException exception = assertThrows(InvalidDomainDataException.class,
                () -> new User(id, name, password, email));

        assertEquals("Invalid email format", exception.getMessage());
    }
}
