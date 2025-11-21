package by.alexeysavchic.voter_pet_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VoteResponse
{
    String pollName;

    String OptionName;
}
