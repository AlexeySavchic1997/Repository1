package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.security.Role;

public interface RoleService
{
    public UserResponse addRole(Long id, Role role);

    public UserResponse removeRole(Long id, Role role);

    public boolean hasRole(Long id, Role role);
}
