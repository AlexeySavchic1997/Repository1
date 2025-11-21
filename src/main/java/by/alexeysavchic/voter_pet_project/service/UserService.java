package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.FilterUserRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import java.util.List;

public interface UserService
{
    public UserResponse findUser(String username);

    public List<UserResponse> getUsers(FilterUserRequest filter, String condition);

    public UserResponse changeCredentials(ChangeCredentialsRequest request);

    public void deleteUser(Long id);
}
