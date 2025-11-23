package by.alexeysavchic.voter_pet_project.controller;


import by.alexeysavchic.voter_pet_project.dto.request.RoleRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.RoleService;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.UserService;
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
    public GetUserResponse addRole(@PathVariable ("id") Long id, @RequestBody RoleRequest request)
    {
        return roleService.addRole(id,request.getRole());
    }

    @DeleteMapping("/removerole/{id}")
    public GetUserResponse removeRole(@PathVariable ("id") Long id, @RequestBody RoleRequest request)
    {
        return roleService.removeRole(id,request.getRole());
    }
}
