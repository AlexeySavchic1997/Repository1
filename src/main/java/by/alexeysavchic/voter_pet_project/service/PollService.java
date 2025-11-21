package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.FilterPollRequset;
import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;

import java.time.LocalDate;
import java.util.List;

public interface PollService
{
    public PollResponse createPoll(PollRequest pollRequest);

    public void deletePoll (Long id);

    public List<PollResponse> getPolls(FilterPollRequset filter, String condition, LocalDate dateCondition);

}
