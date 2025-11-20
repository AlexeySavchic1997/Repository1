package by.alexeysavchic.voter_pet_project.controllers;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.AllPollsResponse;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;
import by.alexeysavchic.voter_pet_project.service.PollService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poll")
public class PollController
{
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("allPolls")
    public List<AllPollsResponse> getAllPolls()
    {
        return pollService.getAllPolls();
    }

    @PostMapping("/createPoll")
    public PollResponse createPoll(@Valid @RequestBody PollRequest request)
    {
        return pollService.createPoll(request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePoll (@PathVariable("id") Long id) {
        pollService.deletePoll(id);
    }

}
