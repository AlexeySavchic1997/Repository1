package by.alexeysavchic.voter_pet_project.dto.request;

import by.alexeysavchic.voter_pet_project.customValidations.UniqueEmail;
import by.alexeysavchic.voter_pet_project.customValidations.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeCredentialsRequest
{
    @Size(min = 2, max = 30, message = "username must be between 2 and 30 symbols")
    @UniqueUsername
    @NotBlank
    private String username;

    @Email(message = "wrong email pattern")
    @UniqueEmail
    @NotBlank
    private String email;

    @Size(min = 6, max = 15, message = "password must be between 6 and 15 symbols")
    @NotBlank
    private String oldPassword;

    @Size(min = 6, max = 15, message = "password must be between 6 and 15 symbols")
    @NotBlank
    private String newPassword;
}
