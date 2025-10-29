package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.LoginRequest;
import by.alexeysavchic.voter_pet_project.dto.request.RegisterRequest;
import by.alexeysavchic.voter_pet_project.dto.response.UserResponse;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exceptions.EmailAlreadyExsistException;
import by.alexeysavchic.voter_pet_project.exceptions.NameAllreadyExsistsException;
import by.alexeysavchic.voter_pet_project.mappers.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.CustomUserDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest
{
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    @DisplayName("User with that name already exist")
    void signUpWhenUserNameAlreadyExist()
    {
        RegisterRequest request= new RegisterRequest("name", "1234", "email@gmail.com");
        User user = new User();
        user.setUsername("name");

        when(userMapper.registerUserToUser(request)).thenReturn(user);
        when(userRepository.findUserByUsername("name")).thenReturn(user);

        assertThrows(NameAllreadyExsistsException.class, () -> {
            authService.signup(request);
        });

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("User with that email already exist")
    void signUpWhenUserEmailAlreadyExist()
    {
        RegisterRequest request= new RegisterRequest("name", "1234", "email@gmail.com");
        User user = new User();
        user.setEmail("email@gmail.com");

        when(userMapper.registerUserToUser(request)).thenReturn(user);
        when(userRepository.findUserByEmail("email@gmail.com")).thenReturn(user);

        assertThrows(EmailAlreadyExsistException.class, () -> {
            authService.signup(request);
        });

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Successful Login")
    void SuccessfulLogin()
    {
        LoginRequest request = new LoginRequest("name", "1234");
        User user = new User();
        user.setId(1L);
        user.setUsername("name");

        Authentication authentication = mock(Authentication.class);
        CustomUserDetails userDetails = new CustomUserDetails(user);

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userMapper.userToUserResponse(user)).thenReturn(new UserResponse());

        UserResponse result = authService.login(request);

        assertNotNull(result);
        verify(authenticationManager).authenticate(any());
    }

}

