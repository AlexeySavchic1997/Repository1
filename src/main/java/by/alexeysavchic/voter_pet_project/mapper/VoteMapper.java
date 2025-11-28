package by.alexeysavchic.voter_pet_project.mapper;

import by.alexeysavchic.voter_pet_project.dto.response.GetVoteResponse;
import by.alexeysavchic.voter_pet_project.entity.Option;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.entity.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class VoteMapper
{
    public abstract GetVoteResponse voteToGetVoteResponse(Vote vote);

    protected String  UserToString(User user)
    {
        if (user==null)
        {
            return null;
        }
    return user.getUsername();
    }

    protected String  OptionToString(Option option)
    {
        if (option==null)
        {
            return null;
        }
        return option.getText();
    }
}
