package by.alexeysavchic.voter_pet_project.controller;

import by.alexeysavchic.voter_pet_project.dto.request.LoginRequest;
import by.alexeysavchic.voter_pet_project.dto.request.UserRegisterRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public GetUserResponse signup(@Valid @RequestBody UserRegisterRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public GetUserResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
