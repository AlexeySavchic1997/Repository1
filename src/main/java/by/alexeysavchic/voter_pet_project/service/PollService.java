package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;

public interface PollService
{
    public PollResponse createPoll(PollRequest pollRequest);

    public void deletePoll (String question);

}
