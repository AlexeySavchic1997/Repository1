package by.alexeysavchic.voter_pet_project.controller;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.GetUsersRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public GetUserResponse findUserById(@PathVariable("id") Long id)
    {
        return userService.findUserById(id);
    }

    @PostMapping
    public List<GetUserResponse> getAllUsers(@RequestBody GetUsersRequest request)
    {
       return userService.getUsers(request);
    }

    @PutMapping
    public GetUserResponse changeCredentials(@Valid @RequestBody ChangeCredentialsRequest request)
    {
        return userService.changeCredentials(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable("id") Long id)
    {
        userService.deleteUser(id);
    }
}
