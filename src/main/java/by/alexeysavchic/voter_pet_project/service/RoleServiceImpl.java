package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.RoleRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.UserNotFoundException;
import by.alexeysavchic.voter_pet_project.mapper.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService
{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public RoleServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public GetUserResponse addRole(RoleRequest request)
    {
        User user = userRepository.findUserById(request.getId()).orElseThrow(()->
                new UserNotFoundException());

        user.getRoles().add(request.getRole());
        userRepository.save(user);

        return userMapper.userToGetUserResponse(user);
    }

    @Override
    public GetUserResponse removeRole(RoleRequest request)
    {
        User user = userRepository.findUserById(request.getId()).orElseThrow(()->
                new UserNotFoundException());

        user.getRoles().remove(request.getRole());
        userRepository.save(user);
        GetUserResponse response=userMapper.userToGetUserResponse(user);

        return response;
    }

}
