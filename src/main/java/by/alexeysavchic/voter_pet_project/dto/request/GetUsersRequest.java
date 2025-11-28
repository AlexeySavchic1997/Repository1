package by.alexeysavchic.voter_pet_project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUsersRequest
{
    @Positive
    private Long id;

    @Size(min = 2, max = 30, message = "username must be between 2 and 30 symbols")
    private String username;

    @Email(message = "wrong email pattern")
    private String email;
}
