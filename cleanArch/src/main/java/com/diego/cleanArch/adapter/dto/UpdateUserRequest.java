package com.diego.cleanArch.adapter.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record UpdateUserRequest(
        @NotNull(message = "Id is required")
        UUID id,

        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must have at least 6 characters")
        String password) {
}
