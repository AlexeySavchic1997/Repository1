package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.LoginRequest;
import by.alexeysavchic.voter_pet_project.dto.request.RegisterRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;

public interface AuthService
{
    public UserResponse signup(RegisterRequest registerRequest);

    public UserResponse login (LoginRequest loginRequest);
}
