package by.alexeysavchic.voter_pet_project.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PollRequest
{
    @NotBlank
    @Size(min = 2, max = 100, message = "question must be between 2 and 100 symbols")
    private String question;

    @Size(max = 300, message = "description must be less than 300 symbols")
    private String description;

    @Min(value = 1, message = "Duration must be at least 1 day")
    @Max(value = 365, message = "Duration can't be more than 365 days")
    private int duration;

    private List<String> options;
}
