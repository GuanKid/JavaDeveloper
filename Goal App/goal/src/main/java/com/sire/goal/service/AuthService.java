package com.sire.goal.service;

import com.sire.goal.models.entities.User;
import com.sire.goal.models.responses.AuthResponse;
import com.sire.goal.repository.UserRepository;
import com.sire.goal.security.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(User request) {
        User user = new User();
        if(request.getName()== null || request.getName().isEmpty()) {
            return new AuthResponse("Please enter your name");
        } else if(request.getUsername() == null || request.getUsername().isEmpty()) {
            return new AuthResponse("Username is required");
        } else if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return new AuthResponse("Password is required");
        }
        try {
            user.setName(request.getName());
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            user = userRepository.save(user);
        }catch (Exception e) {
            return new AuthResponse("Something went wrong");
        }
        String token = jwtService.generateToken(user);

        return new AuthResponse("Account created successfully",token, user);
    }

    public AuthResponse login(User request) {
        try {
            if(request.getUsername() == null || request.getUsername().isEmpty()) {
                return new AuthResponse("Please enter your username");
            }else if(request.getPassword() == null || request.getPassword().isEmpty()) {
                return new AuthResponse("Please enter your password!");
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        }catch (Exception e) {
            return new AuthResponse("Password or Username is incorrect");
        }
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);

        return new AuthResponse("Logged in successfully!",token, user);
    }
}
