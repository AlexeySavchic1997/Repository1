package by.alexeysavchic.voter_pet_project.mapper;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetPollResponse;
import by.alexeysavchic.voter_pet_project.entity.Option;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PollMapper
{
    private final UserMapper userMapper;

    public PollMapper(UserMapper userMapper)
    {

        this.userMapper = userMapper;
    }

    public Poll pollRequestToPoll(PollRequest pollRequest)
    {
        Poll poll = new Poll();
        poll.setQuestion(pollRequest.getQuestion());
        poll.setDescription(pollRequest.getDescription());
        List<Option> options = new ArrayList<>();
        for(String text:pollRequest.getOptions())
        {
            Option option = new Option();
            option.setText(text);
            option.setPoll(poll);
            options.add(option);
        }
        poll.setOptions(options);
        LocalDateTime creationTime=LocalDateTime.now();
        poll.setCreationTime(creationTime);
        poll.setEndingTime(creationTime.plusDays(pollRequest.getDuration()));

        return poll;
    }

    public GetPollResponse pollToPollResponse(Poll poll)
    {
        GetPollResponse getPollResponse = new GetPollResponse();
        getPollResponse.setId(poll.getId());
        getPollResponse.setQuestion(poll.getQuestion());
        getPollResponse.setDescription(poll.getDescription());
        getPollResponse.setCreationTime(poll.getCreationTime());
        getPollResponse.setEndingTime(poll.getEndingTime());
        getPollResponse.setCreatedBy(userMapper.userToUserResponse(poll.getCreatedBy()));
        List<String> options = new ArrayList<>();
        for (Option option:poll.getOptions())
        {
            options.add(option.getText());
        }
        getPollResponse.setOptions(options);
        return getPollResponse;
    }
}
