package com.sire.goal.controller;

import com.sire.goal.models.entities.User;
import com.sire.goal.models.responses.AuthResponse;
import com.sire.goal.service.AuthService;
import com.sire.goal.tools.utils.SpliteToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication",
        description = "Authentication APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final SpliteToken spliteToken;

    public AuthController(AuthService authService, SpliteToken spliteToken) {
        this.authService = authService;
        this.spliteToken = spliteToken;
    }

    @Operation(
            summary = "Create account",
            description = "Create account by entering all fields",
            tags = { "Auth", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(
            summary = "Login into account",
            description = "Login account by entering all required fields",
            tags = { "Auth", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/get/token")
    public ResponseEntity<String> getToken(
            @RequestHeader(name="Authorization") String token
    ) {
        String filteredToken = spliteToken.split(token);
        return ResponseEntity.ok(filteredToken);
    }
}