package com.diego.cleanArch.adapter.controller;

import com.diego.cleanArch.adapter.dto.CreateUserRequest;
import com.diego.cleanArch.adapter.dto.UpdateUserRequest;
import com.diego.cleanArch.adapter.dto.UserResponse;
import com.diego.cleanArch.application.usecase.CreateUserUseCase;
import com.diego.cleanArch.application.usecase.DeleteUserUseCase;
import com.diego.cleanArch.application.usecase.FindAllUserUseCase;
import com.diego.cleanArch.application.usecase.UpdateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/users")
@Tag(name = "Users", description = "User management operations")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final FindAllUserUseCase findAllUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, UpdateUserUseCase updateUserUseCase, FindAllUserUseCase findAllUserUseCase, DeleteUserUseCase deleteUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.findAllUserUseCase = findAllUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @PostMapping
    @Operation(
            summary = "Create a new user",
            description = "Creates a new user with unique email address"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))
            )
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Invalid input data",
//                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
//            ),
//            @ApiResponse(
//                    responseCode = "409",
//                    description = "Email already exists",
//                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
//            )
    })
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request){
        var input = new CreateUserUseCase.Input(request.name(), request.email(), request.password());
        var output = createUserUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(output.id(),output.name(),output.email()));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        List<FindAllUserUseCase.Output> users = findAllUserUseCase.execute();
        List<UserResponse> response = users.stream().map(u -> new UserResponse(u.id(),u.name(),u.email()))
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        deleteUserUseCase.execute(new DeleteUserUseCase.Input(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UpdateUserRequest request){
        var input = new UpdateUserUseCase.Input(request.id(), request.name(), request.email(), request.password());
        var output = updateUserUseCase.execute(input);
        return ResponseEntity.ok(new UserResponse(output.id(), output.name(), output.email()));
    }

}
