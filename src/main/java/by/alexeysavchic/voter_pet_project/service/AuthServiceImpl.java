package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.LoginRequest;
import by.alexeysavchic.voter_pet_project.dto.request.RegisterRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.EmailAlreadyExsistException;
import by.alexeysavchic.voter_pet_project.exception.NameAllreadyExsistsException;
import by.alexeysavchic.voter_pet_project.mapper.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService
{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;


    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    @Override
    public UserResponse signup(RegisterRequest registerRequest)
    {
        User user=userMapper.registerUserToUser(registerRequest);

        if (userRepository.findUserByUsername(user.getUsername())==null)
        {
            if (userRepository.findUserByEmail(user.getEmail())==null)
            {
                user= userRepository.save(user);
            }
            else
            {
                throw new EmailAlreadyExsistException("Email already exists");
            }
        }
        else
        {
            throw new NameAllreadyExsistsException("Name already exists");
        }
        UserResponse userResponse=userMapper.userToUserResponse(user);
        return userResponse;
    }

    @Transactional
    @Override
    public UserResponse login(LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        return userMapper.userToUserResponse(user);

    }
}
