package by.alexeysavchic.voter_pet_project.service;
import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.entity.User;

import by.alexeysavchic.voter_pet_project.exception.OperationDeniedException;
import by.alexeysavchic.voter_pet_project.exception.UserNotFoundException;
import by.alexeysavchic.voter_pet_project.exception.WrongPasswordException;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.SecurityContextService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    SecurityContextService securityContextService;

    @Mock
    ChangeCredentialsRequest request;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("Find not existing user")
    void findNotExistingUser()
    {
        when(userRepository.findUserById(1L)).thenReturn(null);

        assertThrows(UserNotFoundException.class,()->userService.findUserById(1L));
    }


    @Test
    @DisplayName("Change password with wrong entering old password")
    void changePasswordWithWrongOldPassword()
    {
        ChangeCredentialsRequest request= new ChangeCredentialsRequest();
        request.setUsername("name");
        request.setEmail("email@gmail.com");
        User user = new User();

        when(securityContextService.getCurrentUser()).thenReturn(user);
        when(passwordEncoder.matches(request.getOldPassword(), user.getPassword())).thenReturn(false);

        assertThrows(WrongPasswordException.class,()->userService.changeCredentials(request));
        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Delete user when user not exist")
    void deleteUserWhenUserAlreadyDeleted()
    {
        when(userRepository.findUserById(1L)).thenReturn(null);

        assertThrows(UserNotFoundException.class,()->userService.deleteUser(1L));
        verify(userRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Delete user when user not creator")
    void deleteUserWhenUserNotCreator()
    {
        User user = new User();
        user.setUsername("name1");
        User anotherUser = new User();
        anotherUser.setUsername("name2");
        when(userRepository.findUserById(1L)).thenReturn(Optional.of(user));
        when(securityContextService.getCurrentUser()).thenReturn(anotherUser);

        assertThrows(OperationDeniedException.class,()->userService.deleteUser(1L));
        verify(userRepository, never()).delete(any());
    }




}
