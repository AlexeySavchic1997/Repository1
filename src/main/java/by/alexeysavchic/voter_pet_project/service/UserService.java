package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import java.util.List;

public interface UserService
{
    public UserResponse findUser(String username);

    public List<UserResponse> getAllUsers();

    public UserResponse changeCredentials(ChangeCredentialsRequest request);

    public void deleteUser(Long id);
}
