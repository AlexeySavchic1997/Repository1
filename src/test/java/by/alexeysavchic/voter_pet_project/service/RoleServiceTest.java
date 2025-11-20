package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.exception.UserNotFoundException;
import by.alexeysavchic.voter_pet_project.mapper.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest
{
    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    RoleServiceImpl roleService;

    @Test
    @DisplayName("Add Role when user not found")
    void addRoleWhenUserNotFound()
    {
        Long id = 1L;

        when(userRepository.findUserById(id)).thenReturn(null);

        assertThrows(UserNotFoundException.class,()->roleService.addRole(id, Role.ROLE_USER));
        verify(userRepository, never()).save(any());
    }
}
