package by.alexeysavchic.voter_pet_project.mappers;


import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.AllPollsResponse;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;
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

    public PollResponse pollToPollResponse(Poll poll)
    {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setDescription(poll.getDescription());
        pollResponse.setCreationTime(poll.getCreationTime());
        pollResponse.setEndingTime(poll.getEndingTime());
        pollResponse.setCreatedBy(userMapper.userToUserResponse(poll.getCreatedBy()));
        List<String> options = new ArrayList<>();
        for (Option option:poll.getOptions())
        {
            options.add(option.getText());
        }
        pollResponse.setOptions(options);
        return pollResponse;
    }

    public AllPollsResponse pollToAllPollsResponse(Poll poll)
    {
        AllPollsResponse response = new AllPollsResponse(poll.getId(),poll.getQuestion());
        return response;
    }
}
