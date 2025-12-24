package com.diego.cleanArch.adapter.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.diego.cleanArch.adapter.dto.CreateUserRequest;
import com.diego.cleanArch.application.usecase.CreateUserUseCase;
import com.diego.cleanArch.application.usecase.DeleteUserUseCase;
import com.diego.cleanArch.application.usecase.FindAllUserUseCase;
import com.diego.cleanArch.application.usecase.UpdateUserUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockitoBean
        private CreateUserUseCase createUserUseCase;

        @MockitoBean
        private UpdateUserUseCase updateUserUseCase;

        @MockitoBean
        private FindAllUserUseCase findAllUserUseCase;

        @MockitoBean
        private DeleteUserUseCase deleteUserUseCase;

        @Test
        void shouldCreateUserSuccessfully() throws Exception {
                CreateUserRequest request = new CreateUserRequest(
                                "John Doe", "john@example.com", "password123");

                UUID userId = UUID.randomUUID();
                CreateUserUseCase.Output output = new CreateUserUseCase.Output(
                                userId, "John Doe", "john@example.com");

                when(createUserUseCase.execute(any(CreateUserUseCase.Input.class)))
                                .thenReturn(output);

                // Act & Assert
                mockMvc.perform(post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(userId.toString()))
                                .andExpect(jsonPath("$.name").value("John Doe"))
                                .andExpect(jsonPath("$.email").value("john@example.com"))
                                .andExpect(jsonPath("$.password").doesNotExist()); // Senha não deve ser retornada

                verify(createUserUseCase).execute(any(CreateUserUseCase.Input.class));
        }
}
