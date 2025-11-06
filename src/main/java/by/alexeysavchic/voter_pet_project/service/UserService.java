package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.ChangePasswordRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import java.util.List;

public interface UserService
{
    public UserResponse findUser(String username);

    public List<UserResponse> getAllUsers();

    public UserResponse changeUsername(String newName);

    public UserResponse changePassword(ChangePasswordRequest request);

    public UserResponse changeEmail(String newEmail);

    public void deleteUser(Long id);
}
