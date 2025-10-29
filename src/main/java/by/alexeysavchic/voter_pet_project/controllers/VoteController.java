package by.alexeysavchic.voter_pet_project.controllers;

import by.alexeysavchic.voter_pet_project.dto.request.VoteRequest;
import by.alexeysavchic.voter_pet_project.dto.response.CountingResponce;
import by.alexeysavchic.voter_pet_project.dto.response.VoteResponse;
import by.alexeysavchic.voter_pet_project.service.VoteService;
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
    public VoteResponse vote(@Valid @RequestBody VoteRequest request)
    {
        return voteService.voting(request);
    }

    @GetMapping("/count/{question}")
    public List<CountingResponce> counting(@PathVariable ("question") String question)
    {
        return voteService.voteCounting(question);
    }


}
