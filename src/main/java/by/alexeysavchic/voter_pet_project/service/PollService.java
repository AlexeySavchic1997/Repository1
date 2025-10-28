package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.AllPollsResponse;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;

import java.util.List;

public interface PollService
{
    public PollResponse createPoll(PollRequest pollRequest);

    public void deletePoll (Long id);

    public List<AllPollsResponse> getAllPolls();

}
