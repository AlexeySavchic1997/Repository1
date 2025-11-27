package by.alexeysavchic.voter_pet_project.controller;

import by.alexeysavchic.voter_pet_project.dto.request.GetPollsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetPollResponse;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.PollService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/poll")
public class PollController
{
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/getPolls")
    public List<GetPollResponse> getPolls(@Valid @RequestBody GetPollsRequest request)
    {
       return pollService.getPolls(request);
    }

    @PostMapping("/createPoll")
    public GetPollResponse createPoll(@Valid @RequestBody PollRequest request)
    {
        return pollService.createPoll(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePoll (@PathVariable("id") Long id) {
        pollService.deletePoll(id);
    }
}
