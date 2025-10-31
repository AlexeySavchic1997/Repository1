package by.alexeysavchic.voter_pet_project.dto.request;

import by.alexeysavchic.voter_pet_project.security.Role;
import jakarta.validation.constraints.NotNull;

public class RoleRequest
{
    @NotNull(message = "Role is required")
    private Role role;

    // геттеры и сеттеры
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
