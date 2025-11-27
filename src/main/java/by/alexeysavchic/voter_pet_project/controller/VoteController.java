package by.alexeysavchic.voter_pet_project.controller;

import by.alexeysavchic.voter_pet_project.dto.request.VoteRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetCountingResponse;
import by.alexeysavchic.voter_pet_project.dto.response.GetVoteResponse;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.VoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController
{
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public GetVoteResponse vote(@Valid @RequestBody VoteRequest request)
    {
        return voteService.voting(request);
    }

    @GetMapping("/{question}")
    public List<GetCountingResponse> counting(@PathVariable("question") String question)
    {
        return voteService.voteCounting(question);
    }


}
