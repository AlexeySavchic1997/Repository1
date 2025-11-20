package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.VoteRequest;
import by.alexeysavchic.voter_pet_project.entity.Option;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.exception.OptionNotFoundException;
import by.alexeysavchic.voter_pet_project.exception.PollAlreadyEndedException;
import by.alexeysavchic.voter_pet_project.exception.PollNotExistException;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest
{
    @Mock
    private PollRepository pollRepository;

    @InjectMocks
    private VoteServiceImpl voteService;

    @Test
    @DisplayName("Voting when poll doesn't exist")
    public void votingWhenPollNotExist()
    {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setPollName("name");
        when(pollRepository.findPollByQuestion("name")).thenReturn(null);

        assertThrows(PollNotExistException.class, () -> voteService.voting(voteRequest));
    }

    @Test
    @DisplayName("Voting when voting already ended")
    public void votingAlreadyEnded()
    {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setPollName("name");
        Poll poll = new Poll();
        poll.setCreationTime(LocalDateTime.now());
        poll.setEndingTime(LocalDateTime.now().minusDays(1));

        when(pollRepository.findPollByQuestion("name")).thenReturn(poll);

        assertThrows(PollAlreadyEndedException.class, () -> voteService.voting(voteRequest));
    }

    @Test
    @DisplayName("Voting when option not found")
    public void votingWhenOptionNotFound()
    {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setPollName("name");
        Poll poll = new Poll();
        List<Option> options = new ArrayList<>();
        poll.setOptions(options);
        poll.setCreationTime(LocalDateTime.now());
        poll.setEndingTime(LocalDateTime.now().plusDays(1));

        when(pollRepository.findPollByQuestion("name")).thenReturn(poll);

        assertThrows(OptionNotFoundException.class, () -> voteService.voting(voteRequest));
    }

    @Test
    @DisplayName("Voting when option not found")
    public void votingWhenUserAlreadyVoted()
    {
        VoteRequest voteRequest = new VoteRequest("pollName","WrongOption");
        Poll poll = new Poll();
        poll.setQuestion("pollName");
        List<Option> options = new ArrayList<>();
        poll.setCreationTime(LocalDateTime.now());
        poll.setEndingTime(LocalDateTime.now().plusDays(1));
        poll.setOptions(options);

        when(pollRepository.findPollByQuestion("pollName")).thenReturn(poll);


        assertThrows(OptionNotFoundException.class, () -> voteService.voting(voteRequest));
    }

    @Test
    @DisplayName("Voting when option not found")
    public void VoteCountingWhenPollNotExist()
    {
        when(pollRepository.findPollByQuestion("question name")).thenReturn(null);

        assertThrows(PollNotExistException.class, ()->voteService.voteCounting("question name"));
    }
}
