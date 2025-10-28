package by.alexeysavchic.voter_pet_project.controllers;

import by.alexeysavchic.voter_pet_project.dto.request.LoginRequest;
import by.alexeysavchic.voter_pet_project.dto.request.RegisterRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public UserResponse signup(@Valid @RequestBody RegisterRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
