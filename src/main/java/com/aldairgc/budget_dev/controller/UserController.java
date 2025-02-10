package com.aldairgc.budget_dev.controller;

import com.aldairgc.budget_dev.controller.dto.in.UserDtoIn;
import com.aldairgc.budget_dev.controller.dto.out.UserDtoOut;
import com.aldairgc.budget_dev.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User RESTFUL API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a list of registered users")
    @ApiResponse(responseCode = "200", description = "List of users")
    public ResponseEntity<List<UserDtoOut>> findAll() {
        var users = userService.findAll();
        var usersDto = users.stream().map(UserDtoOut::fromDomain).toList();
        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Retrieves a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden access")
    })
    public ResponseEntity<UserDtoOut> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(UserDtoOut.fromDomain(user));
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Creates a new user and returns its data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "422", description = "Invalid data")
    })
    public ResponseEntity<UserDtoOut> create(@RequestBody UserDtoIn userDtoIn) {
        var user = userService.create(UserDtoIn.toDomain(userDtoIn));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(UserDtoOut.fromDomain(user));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Updates a user by id and returns its data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid data")
    })
    public ResponseEntity<UserDtoOut> update(@PathVariable Long id, @RequestBody UserDtoIn userDtoIn) {
        var user = userService.update(id, UserDtoIn.toDomain(userDtoIn));
        return ResponseEntity.ok(UserDtoOut.fromDomain(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Deletes a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
