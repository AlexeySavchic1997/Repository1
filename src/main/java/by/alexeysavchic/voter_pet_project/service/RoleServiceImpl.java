package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exceptions.UserNotFoundException;
import by.alexeysavchic.voter_pet_project.mappers.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
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
    public UserResponse addRole(Long id, Role role) {
        User user = userRepository.findUserById(id);
        if (user==null)
        {
            throw new UserNotFoundException("User not found");
        }
        user.addRole(role);
        userRepository.save(user);
        UserResponse response=userMapper.userToUserResponse(user);

        return response;
    }

    @Override
    public UserResponse removeRole(Long id, Role role) {
        User user = userRepository.findUserById(id);
        if (user==null)
        {
            throw new UserNotFoundException("User not found");
        }
        user.removeRole(role);
        userRepository.save(user);
        UserResponse response=userMapper.userToUserResponse(user);

        return response;
    }

    @Override
    public boolean hasRole(Long id, Role role) {
        User user = userRepository.findUserById(id);
        if (user==null)
        {
            throw new UserNotFoundException("User not found");
        }

        return user.hasRole(role);
    }
}
