package by.alexeysavchic.voter_pet_project.controllers;


import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.security.Role;
import by.alexeysavchic.voter_pet_project.service.RoleService;
import by.alexeysavchic.voter_pet_project.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController
{
    private final UserService userService;
    private final RoleService roleService;

    public RoleController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/addrole/{id}")
    public UserResponse addRole(@PathVariable ("id") Long id,@RequestParam Role role)
    {
        return roleService.addRole(id,role);
    }

    @DeleteMapping("/removerole/{id}")
    public UserResponse removeRole(@PathVariable ("id") Long id,@RequestParam Role role)
    {
        return roleService.removeRole(id,role);
    }
}
