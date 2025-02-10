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

@CrossOrigin
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication RESTFUL API")
public class Authentication {

    private final UserService userService;

    public Authentication(UserService userService) {
        this.userService = userService;
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

}
