package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.ChangePasswordRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;

public interface UserService
{

    public UserResponse findUser(String username);

    public UserResponse changeUsername(String newName);

    public UserResponse changePassword(ChangePasswordRequest request);

    public UserResponse changeEmail(String newEmail);
}
