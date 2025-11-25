package by.alexeysavchic.voter_pet_project.dto.request;


import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GetPollsRequest
{
    FilterPollRequset filterPollRequset;

    String condition;

    @Past
    LocalDate dateCondition;
}
