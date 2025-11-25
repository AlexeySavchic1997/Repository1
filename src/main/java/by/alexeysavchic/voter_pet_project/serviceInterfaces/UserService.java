package by.alexeysavchic.voter_pet_project.serviceInterfaces;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.GetUsersRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import java.util.List;

public interface UserService
{
    public GetUserResponse findUserById(Long id);

    public List<GetUserResponse> getUsers(GetUsersRequest request);

    public GetUserResponse changeCredentials(ChangeCredentialsRequest request);

    public void deleteUser(Long id);
}
