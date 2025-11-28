package by.alexeysavchic.voter_pet_project.serviceInterfaces;

import by.alexeysavchic.voter_pet_project.dto.request.RoleRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;

public interface RoleService
{
    public GetUserResponse addRole(RoleRequest  request);

    public GetUserResponse removeRole(RoleRequest  request);
}
