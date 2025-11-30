package by.alexeysavchic.voter_pet_project.mapper;

import by.alexeysavchic.voter_pet_project.dto.request.UserRegisterRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.security.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class UserMapper
{
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRegisterRequest.getPassword()))")
    public abstract User userRegisterRequestToUser(UserRegisterRequest userRegisterRequest);

    protected Set<Role> enumToRoles()
    {
        Set<Role> set = new HashSet<Role>();
        set.add(Role.ROLE_USER);
        return set;
    }

    public abstract GetUserResponse userToGetUserResponse(User user);

    public abstract List<GetUserResponse> ListUsersToListGetUsersResponse(List<User> users);
}
