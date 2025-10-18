package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequset;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;

public interface PollService
{
    public PollResponse createPoll(PollRequset pollRequset, long id);
}
