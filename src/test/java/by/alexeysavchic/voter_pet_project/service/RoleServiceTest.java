package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.RoleRequest;
import by.alexeysavchic.voter_pet_project.exception.UserNotFoundException;
import by.alexeysavchic.voter_pet_project.mapper.UserMapper;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RoleServiceTest
{
    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    RoleRequest request;

    @InjectMocks
    RoleServiceImpl roleService;

    @Test
    @DisplayName("Add Role when user not found")
    void addRoleWhenUserNotFound()
    {
        request.setId(1L);

        when(userRepository.findUserById(request.getId())).thenReturn(Optional.ofNullable(null));

        assertThrows(UserNotFoundException.class,()->roleService.addRole(request));
        verify(userRepository, never()).save(any());
    }
}
