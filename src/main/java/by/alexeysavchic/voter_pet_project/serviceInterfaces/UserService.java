package by.alexeysavchic.voter_pet_project.serviceInterfaces;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.FilterUserRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import java.util.List;

public interface UserService
{
    public GetUserResponse findUser(String username);

    public List<GetUserResponse> getUsers(FilterUserRequest filter, String condition);

    public GetUserResponse changeCredentials(ChangeCredentialsRequest request);

    public void deleteUser(Long id);
}
