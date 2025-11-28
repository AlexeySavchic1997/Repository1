package by.alexeysavchic.voter_pet_project.dto.request;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GetPollsRequest
{
    @Positive
    private Long id;

    @Size(min = 2, max = 100, message = "question must be between 2 and 100 symbols")
    private String question;

    @Size(max = 300, message = "description must be less than 300 symbols")
    private String description;

    @Past
    private LocalDateTime creationTime;

    private LocalDateTime endingTime;

    @Size(min = 2, max = 30, message = "username must be between 2 and 30 symbols")
    private String username;
}
