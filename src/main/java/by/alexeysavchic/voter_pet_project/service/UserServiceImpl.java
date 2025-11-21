package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.FilterUserRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.*;
import by.alexeysavchic.voter_pet_project.mapper.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
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
    public UserResponse findUser(String username)
    {
        User user= userRepository.findUserByUsername(username);
        if (user==null)
        {
            throw new UserNotFoundException("User not found");
        }

        UserResponse userResponse=userMapper.userToUserResponse(user);

        return userResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(FilterUserRequest filter, String condition)
    {
        if (filter!=null && condition==null)
        {
            throw new WrongFilterConditionException("wrong filter condition");
        }
        List<UserResponse> response = new ArrayList<>();
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
    public UserResponse changeCredentials(ChangeCredentialsRequest request)
    {
        User user = userRepository.findUserById(securityContextService.getCurrentUser().getId());
        if (user==null)
        {
            throw new UserNotFoundException("user not found");
        }
        if(request.getUsername()!=null)
        {
            if(userRepository.findUserByUsername(request.getUsername())==null)
            {
            user.setUsername(request.getUsername());
            }
            else
            {
                throw new NameAllreadyExsistsException("name already exist");
            }
        }
        if (request.getEmail()!=null)
        {
            if(userRepository.findUserByEmail(request.getEmail())==null)
            {
                user.setEmail(request.getEmail());
            }
            else
            {
                throw new EmailAlreadyExsistException("email already exists");
            }
        }
        if (request.getNewPassword()!=null)
        {
            if (passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
            {
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            }
            else
            {
                throw new WrongPasswordException("Wrong Password");
            }
        }
        return userMapper.userToUserResponse(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id)
    {
        User user = userRepository.findUserById(id);
        if (user==null)
        {
            throw new UserNotFoundException("user not found");
        }
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
