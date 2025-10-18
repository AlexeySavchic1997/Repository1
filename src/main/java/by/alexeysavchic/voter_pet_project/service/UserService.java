package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.UserRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;

public interface UserService
{
    public UserResponse createUser(UserRequest userRequest);

    public UserResponse findUser(String username);

    public UserResponse changeUsername(String nameForChanging, String newName);

    public UserResponse changePassword(String name, String passwordForChanging, String newPassword);

    public UserResponse changeEmail(String name, String emailForChanging, String newEmail);
}
