package by.alexeysavchic.voter_pet_project.serviceInterfaces;

import by.alexeysavchic.voter_pet_project.dto.request.GetPollsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetPollResponse;
import java.util.List;

public interface PollService
{
    public GetPollResponse createPoll(PollRequest pollRequest);

    public void deletePoll (Long id);

    public List<GetPollResponse> getPolls(GetPollsRequest request);

}
