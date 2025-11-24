package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.UserNotFoundException;
import by.alexeysavchic.voter_pet_project.mapper.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public GetUserResponse addRole(Long id, Role role)
    {
        User user = userRepository.findUserById(id).orElseThrow(()->
                new UserNotFoundException());

        user.addRole(role);
        userRepository.save(user);

        return userMapper.userToUserResponse(user);
    }

    @Override
    @Transactional
    public GetUserResponse removeRole(Long id, Role role)
    {
        User user = userRepository.findUserById(id).orElseThrow(()->
                new UserNotFoundException());

        user.removeRole(role);
        userRepository.save(user);
        GetUserResponse response=userMapper.userToUserResponse(user);

        return response;
    }

    @Override
    @Transactional
    public boolean hasRole(Long id, Role role)
    {
        User user = userRepository.findUserById(id).orElseThrow(()->
                new UserNotFoundException());

        return user.hasRole(role);
    }
}
