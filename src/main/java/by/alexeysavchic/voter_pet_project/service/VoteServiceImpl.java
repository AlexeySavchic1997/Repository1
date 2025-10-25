package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.VoteRequest;
import by.alexeysavchic.voter_pet_project.dto.response.CountingResponce;
import by.alexeysavchic.voter_pet_project.dto.response.VoteResponse;
import by.alexeysavchic.voter_pet_project.entity.Option;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.entity.Vote;
import by.alexeysavchic.voter_pet_project.exceptions.OptionNotFoundException;
import by.alexeysavchic.voter_pet_project.exceptions.PollAlreadyEndedException;
import by.alexeysavchic.voter_pet_project.exceptions.PoolNotExistException;
import by.alexeysavchic.voter_pet_project.exceptions.UserAlreadyVotedException;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import by.alexeysavchic.voter_pet_project.repository.VoteRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class VoteServiceImpl implements VoteService
{
    private final VoteRepository voteRepository;

    private final PollRepository pollRepository;

    private final SecurityContextServiceImpl securityContextService;

    public VoteServiceImpl(VoteRepository voteRepository, PollRepository pollRepository, SecurityContextServiceImpl securityContextService) {
        this.voteRepository = voteRepository;
        this.pollRepository = pollRepository;
        this.securityContextService = securityContextService;
    }

    @Override
    @Transactional
    public VoteResponse voting(VoteRequest voteRequest)
    {
       Poll poll = pollRepository.findPollByQuestion(voteRequest.getPollName());
       if (poll==null)
       {
            throw new PoolNotExistException("Poll doesn't exist");
       }
       if (!poll.isActive())
       {
           throw new PollAlreadyEndedException("Poll already end");
       }
       Option option = poll.getOptions().stream().filter(opt -> opt.getText().equals(voteRequest.getOptionName())).
               findFirst().orElseThrow(()->new OptionNotFoundException("Option not found"));

       User user = securityContextService.getCurrentUser();

       if (!voteRepository.existsByUserAndOption_Poll(user, poll))
       {
            throw new UserAlreadyVotedException("User already voted");
       }
       Vote vote = new Vote();
       vote.setOption(option);
       vote.setUser(user);
       user.addVote(vote);
       option.addVote(vote);
       voteRepository.save(vote);

       VoteResponse voteResponse=new VoteResponse(poll.getQuestion(), option.getText());

       return voteResponse;
    }


    @Override
    @Transactional
    public List<CountingResponce> voteCounting(String question)
    {
        Poll poll=pollRepository.findPollByQuestion(question);
        List<Option> options = poll.getOptions();
        List<CountingResponce> response = new ArrayList<>();

        for (Option option:options)
        {
            Integer count = (int) option.getVotes().stream().count();
            CountingResponce countingResponce = new CountingResponce(option.getText(),count);
            response.add(countingResponce);
        }
        return response;
    }
}
