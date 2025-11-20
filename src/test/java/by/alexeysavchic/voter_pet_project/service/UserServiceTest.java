package by.alexeysavchic.voter_pet_project.service;
import by.alexeysavchic.voter_pet_project.dto.request.ChangeCredentialsRequest;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.*;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        when(userRepository.findUserByUsername("name")).thenReturn(null);

        assertThrows(UserNotFoundException.class,()->userService.findUser("name"));
    }

//    @Test
//    @DisplayName("Change name when name already exist")
//    void changeNameWhenNameAlreadyExist()
//    {
//        User user = new User();
//        user.setUsername("name");
//        when(userRepository.findUserById(securityContextService.getCurrentUser().getId())).thenReturn(user);
//        when(userRepository.findUserByUsername("name")).thenReturn(user);
//        when(request.getUsername()).thenReturn("name");
//
//        assertThrows(NameAllreadyExsistsException.class,()->userService.changeCredentials(request));
//
//        verify(userRepository, never()).save(any());
//    }

//    @Test
//    @DisplayName("Change password with wrong entering old password")
//    void changePasswordWithWrongOldPassword()
//    {
//        ChangePasswordRequest request= new ChangePasswordRequest();
//        User user = new User();
//
//        when(securityContextService.getCurrentUser()).thenReturn(user);
//        when(passwordEncoder.matches(request.getOldPassword(), user.getPassword())).thenReturn(false);
//
//        assertThrows(WrongPasswordException.class,()->userService.changePassword(request));
//        verify(userRepository, never()).save(any());
//    }
//
//    @Test
//    @DisplayName("Change email when email already exist")
//    void changeEmailWhenEmailAlreadyExist()
//    {
//        User user = new User();
//        when(userRepository.findUserByEmail("email@gmail.com")).thenReturn(user);
//
//        assertThrows(EmailAlreadyExsistException.class,()->userService.changeEmail("email@gmail.com"));
//
//        verify(userRepository, never()).save(any());
//    }

    @Test
    @DisplayName("Change email when email already exist")
    void deleteUserWhenUserAlreadyExist()
    {
        when(userRepository.findUserById(1L)).thenReturn(null);

        assertThrows(UserNotFoundException.class,()->userService.deleteUser(1L));
        verify(userRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Change email when email already exist")
    void deleteUserWhenUserNotCreator()
    {
        User user = new User();
        user.setUsername("name1");
        User anotherUser = new User();
        anotherUser.setUsername("name2");
        when(userRepository.findUserById(1L)).thenReturn(user);
        when(securityContextService.getCurrentUser()).thenReturn(anotherUser);

        assertThrows(OperationDeniedException.class,()->userService.deleteUser(1L));
        verify(userRepository, never()).delete(any());
    }




}
