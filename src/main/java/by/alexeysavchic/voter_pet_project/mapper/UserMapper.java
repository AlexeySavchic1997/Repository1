package by.alexeysavchic.voter_pet_project.mapper;

import by.alexeysavchic.voter_pet_project.dto.request.UserRegisterRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.security.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper
{
    PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUserToUser(UserRegisterRequest userRegisterRequest)
    {
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.addRole(Role.ROLE_USER);

        return user;
    }

    public GetUserResponse userToUserResponse(User user)
    {
        GetUserResponse getUserResponse = new GetUserResponse();
        getUserResponse.setId(user.getId());
        getUserResponse.setUsername(user.getUsername());
        getUserResponse.setEmail(user.getEmail());
        getUserResponse.setRoles(user.getRoles());

        return getUserResponse;
    }
}
