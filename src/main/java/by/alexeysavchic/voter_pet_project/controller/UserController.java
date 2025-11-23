package by.alexeysavchic.voter_pet_project.controller;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.FilterUserRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.UserService;
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
    public GetUserResponse findUser(@PathVariable ("username") String username)
    {
        return userService.findUser(username);
    }

    @GetMapping({"/allUsers"})
    public List<GetUserResponse> getAllUsers(@RequestParam(required = false, value="filter")FilterUserRequest filter,
                                             @RequestParam(required = false, value="condition") String condition)
    {
       return userService.getUsers(filter,condition);
    }

    @PutMapping("/changeCredentials")
    public GetUserResponse changeCredentials(@Valid @RequestBody ChangeCredentialsRequest request)
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
