package by.alexeysavchic.voter_pet_project.mappers;


import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;
import by.alexeysavchic.voter_pet_project.entity.Option;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.repository.UserRepositoy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PollMapper
{
    UserRepositoy userRepositoy;

    UserMapper userMapper;

    public PollMapper(UserRepositoy userRepositoy, UserMapper userMapper) {
        this.userRepositoy = userRepositoy;
        this.userMapper = userMapper;
    }

    public Poll pollRequestToPoll(PollRequest pollRequest, long id)
    {
        Poll poll = new Poll();
        poll.setCreatedBy(userRepositoy.getById(id));
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
        return pollResponse;
    }
}
