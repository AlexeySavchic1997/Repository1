package by.alexeysavchic.voter_pet_project.dto.request;

import by.alexeysavchic.voter_pet_project.security.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleRequest
{
    @NotNull(message = "Role is required")
    private Role role;
}
