package by.alexeysavchic.voter_pet_project.validation;

import by.alexeysavchic.voter_pet_project.customValidations.UniqueEmailValidator;
import by.alexeysavchic.voter_pet_project.customValidations.UniqueUsernameValidator;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomValidationTest
{
    @Mock
    UserRepository repository;

    @Mock
    ConstraintValidatorContext validatorContext;

    @InjectMocks
    UniqueUsernameValidator usernameValidator;

    @InjectMocks
    UniqueEmailValidator emailValidator;

    @Test
    @DisplayName("Create user with already existing username")
    public void userCreateWithExistingUsername()
    {
        when(repository.existsByUsername("username")).thenReturn(true);

        boolean result=usernameValidator.isValid("username", validatorContext);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Create user with already existing email")
    public void userCreateWithExistingEmail()
    {
        when(repository.existsByEmail("email@gmail.com")).thenReturn(true);

        boolean result=emailValidator.isValid("email@gmail.com", validatorContext);

        Assertions.assertFalse(result);
    }
}
