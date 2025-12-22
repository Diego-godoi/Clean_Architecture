package com.diego.cleanArch.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "User creation request")
public record CreateUserRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        @Schema(description = "User full name",example = "John Doe")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Schema(description = "User email",example = "jonh@example.com")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must have at least 6 characters")
        @Schema(description = "User password (min 6 characters)", example = "123456")
        String password) {
}
