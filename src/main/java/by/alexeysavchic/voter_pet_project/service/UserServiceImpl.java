package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
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

    public UserServiceImpl(UserRepositoy userRepositoy, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepositoy = userRepositoy;
        this.userMapper = userMapper;
        this.passwordEncoder=passwordEncoder;

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
    public UserResponse changeUsername(String nameForChanging, String newName)
    {
        if (userRepositoy.findUserByUsername(newName)==null) {
            User user = userRepositoy.findUserByUsername(nameForChanging);
            user.setUsername(newName);
            user=userRepositoy.save(user);
            UserResponse userResponse=userMapper.userToUserResponse(user);
            return userResponse;
        }
        else
        {
            throw new NameAllreadyExsistsException("Name already exists");
        }
    }

    @Override
    @Transactional
    public UserResponse changePassword(String name, String passwordForChanging, String newPassword)
    {
        User user=userRepositoy.findUserByUsername(name);

        if (passwordEncoder.matches(passwordForChanging, user.getPassword()))
        {
        user.setPassword(passwordEncoder.encode(newPassword));
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
    public UserResponse changeEmail(String name, String emailForChanging, String newEmail)
    {
        User user = new User();
        if (userRepositoy.findUserByEmail(newEmail)==null)
        {
        user=userRepositoy.findUserByUsername(name);
        user.setEmail(newEmail);
        user=userRepositoy.save(user);
        }

        UserResponse userResponse = userMapper.userToUserResponse(user);

        return userResponse;
    }


}
