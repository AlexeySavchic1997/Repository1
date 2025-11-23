package by.alexeysavchic.voter_pet_project.serviceInterfaces;

import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.security.Role;

public interface RoleService
{
    public GetUserResponse addRole(Long id, Role role);

    public GetUserResponse removeRole(Long id, Role role);

    public boolean hasRole(Long id, Role role);
}
