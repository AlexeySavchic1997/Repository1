package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.VoteRequest;
import by.alexeysavchic.voter_pet_project.dto.response.CountingResponce;
import by.alexeysavchic.voter_pet_project.dto.response.VoteResponse;

import java.util.List;

public interface VoteService
{
    public VoteResponse voting(VoteRequest voteRequest);

    public List<CountingResponce> voteCounting(String question);
}
