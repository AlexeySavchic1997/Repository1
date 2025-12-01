package by.alexeysavchic.voter_pet_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest
{
    @NotBlank
    @Size(min = 2, max = 100, message = "question must be between 2 and 100 symbols")
    private String pollName;

    @NotBlank
    private String optionName;
}
