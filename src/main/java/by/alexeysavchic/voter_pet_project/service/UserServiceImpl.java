package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.ChangePasswordRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exceptions.EmailAlreadyExsistException;
import by.alexeysavchic.voter_pet_project.mappers.UserMapper;
import by.alexeysavchic.voter_pet_project.exceptions.NameAllreadyExsistsException;
import by.alexeysavchic.voter_pet_project.exceptions.UserNotFoundException;
import by.alexeysavchic.voter_pet_project.exceptions.WrongPasswordException;
import by.alexeysavchic.voter_pet_project.repository.UserRepositoy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService
{

    private final UserRepositoy userRepositoy;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextServiceImpl securityContextService;

    public UserServiceImpl(UserRepositoy userRepositoy, UserMapper userMapper, PasswordEncoder passwordEncoder, SecurityContextServiceImpl securityContextService) {
        this.userRepositoy = userRepositoy;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.securityContextService = securityContextService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findUser(String username)
    {

        User user=userRepositoy.findUserByUsername(username);
        if (user==null)
        {
            throw new UserNotFoundException("User not found");
        }

        UserResponse userResponse=userMapper.userToUserResponse(user);

        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse changeUsername(String newName)
    {
        if (userRepositoy.findUserByUsername(newName)!=null)
        {
            throw new NameAllreadyExsistsException("Name already exists");
        }
        User user =securityContextService.getCurrentUser();
        user.setUsername(newName);
        userRepositoy.save(user);
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
        userRepositoy.save(user);

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
        User user = new User();
        if (userRepositoy.findUserByEmail(newEmail)!=null)
        {
        throw new EmailAlreadyExsistException("Email already exists");
        }
        user=securityContextService.getCurrentUser();
        user.setEmail(newEmail);
        user=userRepositoy.save(user);

        UserResponse userResponse = userMapper.userToUserResponse(user);

        return userResponse;
    }


}
