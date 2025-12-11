package com.diego.cleanArch.adapter.controller;

import com.diego.cleanArch.application.usecase.CreateUserUseCase;
import com.diego.cleanArch.application.usecase.DeleteUserUseCase;
import com.diego.cleanArch.application.usecase.FindAllUserUseCase;
import com.diego.cleanArch.application.usecase.UpdateUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/users")
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
    public ResponseEntity<CreateUserUseCase.Output> create(@RequestBody CreateUserUseCase.Input input){
        var output = createUserUseCase.execute(input);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FindAllUserUseCase.Output>> findAll(){
        List<FindAllUserUseCase.Output> users = findAllUserUseCase.execute();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        deleteUserUseCase.execute(new DeleteUserUseCase.Input(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UpdateUserUseCase.Output> update(@RequestBody UpdateUserUseCase.Input input){
        var replaced = updateUserUseCase.execute(input);
        return ResponseEntity.ok(replaced);
    }

}
