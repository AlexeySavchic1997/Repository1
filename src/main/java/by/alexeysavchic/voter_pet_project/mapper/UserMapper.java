package by.alexeysavchic.voter_pet_project.mapper;

import by.alexeysavchic.voter_pet_project.dto.request.UserRegisterRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class UserMapper
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "role", constant = "ROLE_USER")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRegisterRequest.getPassword())")
    public abstract User userRegisterRequestToUser(UserRegisterRequest userRegisterRequest);

    public abstract GetUserResponse userToGetUserResponse(User user);
}
