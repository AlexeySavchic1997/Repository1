package by.alexeysavchic.voter_pet_project.controllers;

import by.alexeysavchic.voter_pet_project.dto.request.ChangePasswordRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public UserResponse findUser(@PathVariable ("username") String username)
    {
        return userService.findUser(username);
    }

    @PutMapping("/change_name")
    public UserResponse changeUsername(@RequestParam String newName)
    {
        return userService.changeUsername(newName);
    }

    @PutMapping("/change_email")
    public UserResponse changeEmail(@RequestParam String newEmail)
    {
        return userService.changeEmail(newEmail);
    }

    @PutMapping("/change_password")
    public UserResponse changePassword(@RequestBody ChangePasswordRequest request)
    {
        return userService.changePassword(request);
    }
}
