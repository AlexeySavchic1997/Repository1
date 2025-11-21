package by.alexeysavchic.voter_pet_project.controller;

import by.alexeysavchic.voter_pet_project.dto.request.FilterPollRequset;
import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetPollResponse;
import by.alexeysavchic.voter_pet_project.service.PollService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/poll")
public class PollController
{
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("getPolls")
    public List<GetPollResponse> getPolls(@RequestParam(value = "filter", required = false)FilterPollRequset filter,
                                          @RequestParam(value="textCondition", required = false)String condition,
                                          @RequestParam(value = "dateCondition", required = false) LocalDate timeCondition)
    {
       return pollService.getPolls(filter,condition,timeCondition);
    }

    @PostMapping("/createPoll")
    public GetPollResponse createPoll(@Valid @RequestBody PollRequest request)
    {
        return pollService.createPoll(request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePoll (@PathVariable("id") Long id) {
        pollService.deletePoll(id);
    }

}
