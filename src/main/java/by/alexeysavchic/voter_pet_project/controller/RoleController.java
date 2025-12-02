package by.alexeysavchic.voter_pet_project.controller;

import by.alexeysavchic.voter_pet_project.dto.request.RoleRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.RoleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/role")
public class RoleController
{
    private final RoleService roleService;

    public RoleController(RoleService roleService) {

        this.roleService = roleService;
    }

    @PostMapping
    public GetUserResponse addRole(@RequestBody RoleRequest request)
    {
        return roleService.addRole(request);
    }

    @DeleteMapping
    public GetUserResponse removeRole(@RequestBody RoleRequest request)
    {
        return roleService.removeRole(request);
    }
}
