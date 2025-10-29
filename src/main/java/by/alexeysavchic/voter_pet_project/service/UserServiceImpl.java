package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.ChangePasswordRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exceptions.*;
import by.alexeysavchic.voter_pet_project.mappers.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
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

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, SecurityContextService securityContextService) {
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
    public List<UserResponse> getAllUsers()
    {
        List<UserResponse> response = new ArrayList<>();
        List<User> users= userRepository.findAll();
        for(User user: users)
        {
            response.add(userMapper.userToUserResponse(user));
        }
        return response;
    }

    @Override
    @Transactional
    public UserResponse changeUsername(String newName)
    {
        if (userRepository.findUserByUsername(newName)!=null)
        {
            throw new NameAllreadyExsistsException("Name already exists");
        }
        User user =securityContextService.getCurrentUser();
        user.setUsername(newName);
        userRepository.save(user);
        UserResponse userResponse = userMapper.userToUserResponse(user);
        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse changePassword(ChangePasswordRequest request)
    {
        User user=securityContextService.getCurrentUser();

        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
        {
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        UserResponse userResponse=userMapper.userToUserResponse(user);
        return userResponse;
        }
        else
        {
            throw new WrongPasswordException("Wrong Password");
        }

    }

    @Override
    @Transactional
    public UserResponse changeEmail(String newEmail)
    {
        if (userRepository.findUserByEmail(newEmail)!=null)
        {
        throw new EmailAlreadyExsistException("Email already exists");
        }
        User user=securityContextService.getCurrentUser();
        user.setEmail(newEmail);
        user= userRepository.save(user);

        UserResponse userResponse = userMapper.userToUserResponse(user);

        return userResponse;
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
