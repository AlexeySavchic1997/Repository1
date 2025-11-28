package by.alexeysavchic.voter_pet_project.mapper;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetPollResponse;
import by.alexeysavchic.voter_pet_project.entity.Option;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class},
        unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class PollMapper
{
    @Autowired
    private UserMapper userMapper;

    @Mapping(target = "creationTime", expression = "java(LocalDateTime.now())")
    @Mapping(target = "endingTime", expression = "java(LocalDateTime.now().plusDays(pollRequest.getDuration()))")
    public abstract Poll pollRequestToPoll(PollRequest pollRequest);

    protected Option stringToOption(String text)
    {
        if (text==null)
        {
            return null;
        }
        Option option = Option.builder().text(text).build();
        return option;
    }

    @AfterMapping
    protected void addPollToOptions(@MappingTarget Poll poll)
    {
        if(poll.getOptions()!=null)
        {
            poll.getOptions().forEach(option -> option.setPoll(poll));
        }
    }

    public abstract GetPollResponse pollToGetPollResponse(Poll poll);

    protected String optionToString(Option option)
    {
        return option.getText();
    }

    public abstract List<GetPollResponse> ListPollToListGetPollResponse(List<Poll> polls);




}
