package by.alexeysavchic.voter_pet_project.controllers;

import by.alexeysavchic.voter_pet_project.dto.request.ChangePasswordRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Validated
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

    @GetMapping("/all_users")
    public List<UserResponse> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PutMapping("/change_name")
    public UserResponse changeUsername(@RequestParam @NotBlank @Size(min = 2, max = 30) String newName)
    {
        return userService.changeUsername(newName);
    }

    @PutMapping("/change_email")
    public UserResponse changeEmail(@RequestParam @NotBlank @Email String newEmail)
    {
        return userService.changeEmail(newEmail);
    }

    @PutMapping("/change_password")
    public UserResponse changePassword(@Valid @RequestBody ChangePasswordRequest request)
    {
        return userService.changePassword(request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable("id") Long id)
    {
        userService.deleteUser(id);
    }
}
