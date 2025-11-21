package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.VoteRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetCountingResponse;
import by.alexeysavchic.voter_pet_project.dto.response.GetVoteResponse;

import java.util.List;

public interface VoteService
{
    public GetVoteResponse voting(VoteRequest voteRequest);

    public List<GetCountingResponse> voteCounting(String question);
}
