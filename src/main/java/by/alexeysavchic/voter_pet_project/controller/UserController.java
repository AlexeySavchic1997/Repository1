package by.alexeysavchic.voter_pet_project.controller;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.service.UserService;
import jakarta.validation.Valid;
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

    @GetMapping("/allUsers")
    public List<UserResponse> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PutMapping("/changeCredentials")
    public UserResponse changeCredentials(@Valid @RequestBody ChangeCredentialsRequest request)
    {
        return userService.changeCredentials(request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable("id") Long id)
    {
        userService.deleteUser(id);
    }
}
