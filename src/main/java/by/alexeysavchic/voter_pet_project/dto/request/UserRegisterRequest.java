package by.alexeysavchic.voter_pet_project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequest
{
    @NotBlank
    @Size(min = 2, max = 30, message = "username must be between 2 and 30 symbols")
    private String username;

    @NotBlank
    @Size(min = 6, max = 15, message = "password must be between 6 and 15 symbols")
    private String password;

    @NotBlank
    @Email(message = "wrong email pattern")
    private String email;
}
