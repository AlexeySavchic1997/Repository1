package by.alexeysavchic.voter_pet_project.controller;

import by.alexeysavchic.voter_pet_project.dto.request.VoteRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetCountingResponse;
import by.alexeysavchic.voter_pet_project.dto.response.GetVoteResponse;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.VoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vote")
public class VoteController
{
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/voting")
    public GetVoteResponse vote(@Valid @RequestBody VoteRequest request)
    {
        return voteService.voting(request);
    }

    @GetMapping("/count/{question}")
    public List<GetCountingResponse> counting(@PathVariable ("question") String question)
    {
        return voteService.voteCounting(question);
    }


}
