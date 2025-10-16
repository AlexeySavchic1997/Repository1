package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.UserRequset;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exceptions.UserAllreadyExsistsException;
import by.alexeysavchic.voter_pet_project.exceptions.WrongPasswordException;
import by.alexeysavchic.voter_pet_project.repository.UserRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService
{

    private UserRepositoy userRepositoy;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoy userRepositoy, PasswordEncoder passwordEncoder) {
        this.userRepositoy = userRepositoy;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequset userRequset)
    {
        User user=new User();
        user.setUsername(userRequset.getUsername());
        user.setEmail(userRequset.getEmail());
        user.setPassword(userRequset.getPassword());
        if (userRepositoy.findUserByUsername(user.getUsername())==null)
        {
        userRepositoy.save(user);
        }
        else
        {
            throw new UserAllreadyExsistsException("User already exists");
        }

        UserResponse userResponse = new UserResponse();
        user=userRepositoy.findUserByUsername(user.getUsername());
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse findUser(String username)
    {
        User user = new User();
        user=userRepositoy.findUserByUsername(username);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse changeUsername(String nameForChanging, String newName)
    {
        User user = new User();
        user=userRepositoy.findUserByUsername(nameForChanging);
        user.setUsername(newName);
        userRepositoy.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse changePassword(String name, String passwordForChanging, String newPassword)
    {
        User user = new User();
        user=userRepositoy.findUserByUsername(name);
        if (user.getPassword().equals(passwordForChanging))
        {
        user.setPassword(newPassword);
        }
        else
        {
            throw new WrongPasswordException("Wron Password");
        }
        userRepositoy.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse changeEmail(String name, String emailForChanging, String newEmail)
    {
        User user = new User();
        user=userRepositoy.findUserByUsername(name);
        user.setEmail(newEmail);
        userRepositoy.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }


}
