package by.alexeysavchic.voter_pet_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetVoteResponse
{
    String pollName;

    String OptionName;
}
