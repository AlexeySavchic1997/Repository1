package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.LoginRequest;
import by.alexeysavchic.voter_pet_project.dto.request.UserRegisterRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;

public interface AuthService
{
    public GetUserResponse signup(UserRegisterRequest userRegisterRequest);

    public GetUserResponse login (LoginRequest loginRequest);
}
