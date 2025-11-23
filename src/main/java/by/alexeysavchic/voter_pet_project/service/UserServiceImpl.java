package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.FilterUserRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetUserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.*;
import by.alexeysavchic.voter_pet_project.mapper.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
import by.alexeysavchic.voter_pet_project.security.SecurityContextService;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextService securityContextService;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, SecurityContextService securityContextService)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.securityContextService = securityContextService;
    }

    @Override
    @Transactional(readOnly = true)
    public GetUserResponse findUser(String username)
    {
        User user = userRepository.findUserByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("User not found with username: " + username));

        GetUserResponse getUserResponse=userMapper.userToUserResponse(user);

        return getUserResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetUserResponse> getUsers(FilterUserRequest filter, String condition)
    {
        if (filter!=null && condition==null)
        {
            throw new WrongFilterConditionException("wrong filter condition");
        }
        List<GetUserResponse> response = new ArrayList<>();
        List<User> users= userRepository.findAll();
        switch (filter)
        {
            case USERNAME:
                users=users.stream().filter(user -> (user.getUsername().contains(condition))).toList();
                break;
            case EMAIL:
                users=users.stream().filter(user -> (user.getEmail().contains(condition))).toList();
                break;
            default:
                break;
        }
        for(User user: users)
        {
            response.add(userMapper.userToUserResponse(user));
        }
        return response;
    }

    @Override
    @Transactional
    public GetUserResponse changeCredentials(ChangeCredentialsRequest request)
    {
        User user = userRepository.findUserById(securityContextService.getCurrentUser().getId()).orElseThrow(()->
                new UserNotFoundException("User not found"));;

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
        {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }
        else
        {
            throw new WrongPasswordException("Wrong Password");
        }

        return userMapper.userToUserResponse(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id)
    {
        User user = userRepository.findUserById(id).orElseThrow(()->
                new UserNotFoundException("User not found"));

        if ((securityContextService.getCurrentUser().hasRole(Role.ROLE_ADMIN))||
        securityContextService.getCurrentUser().equals(user))
        {
            userRepository.delete(user);
        }
        else
        {
            throw new OperationDeniedException("operation denied");
        }
    }


}
