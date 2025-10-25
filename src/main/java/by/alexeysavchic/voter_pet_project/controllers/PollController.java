package by.alexeysavchic.voter_pet_project.controllers;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;
import by.alexeysavchic.voter_pet_project.service.PollService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/poll")
public class PollController
{
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/create_poll")
    public PollResponse createPoll(@RequestBody PollRequest request) {
        return pollService.createPoll(request);
    }

    @DeleteMapping("/delete/{question}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePoll (@RequestParam ("question") String question)
    {
        pollService.deletePoll(question);
    }
}
