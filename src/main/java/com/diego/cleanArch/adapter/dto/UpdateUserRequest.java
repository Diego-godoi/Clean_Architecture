package com.diego.cleanArch.adapter.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User update request")
public record UpdateUserRequest(
        @NotNull(message = "Id is required")
        @Schema(description = "User ID", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,

        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        @Schema(description = "New user name", example = "John Update")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Schema(description = "New user email", example = "johnUpdate@example.com")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must have at least 6 characters")
        @Schema(description = "New user password", example = "Updatepassword")
        String password) {
}
