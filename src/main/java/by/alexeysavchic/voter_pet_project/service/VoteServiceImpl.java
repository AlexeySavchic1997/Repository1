package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.VoteRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetCountingResponse;
import by.alexeysavchic.voter_pet_project.dto.response.GetVoteResponse;
import by.alexeysavchic.voter_pet_project.entity.Option;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.entity.Vote;
import by.alexeysavchic.voter_pet_project.exception.OptionNotFoundException;
import by.alexeysavchic.voter_pet_project.exception.PollAlreadyEndedException;
import by.alexeysavchic.voter_pet_project.exception.PollNotExistException;
import by.alexeysavchic.voter_pet_project.exception.UserAlreadyVotedException;
import by.alexeysavchic.voter_pet_project.exception.UserNotFoundException;
import by.alexeysavchic.voter_pet_project.mapper.VoteMapper;
import by.alexeysavchic.voter_pet_project.repository.OptionRepository;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import by.alexeysavchic.voter_pet_project.repository.UserRepository;
import by.alexeysavchic.voter_pet_project.repository.VoteRepository;
import by.alexeysavchic.voter_pet_project.security.SecurityContextServiceImpl;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.VoteService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService
{
    private final VoteRepository voteRepository;

    private final PollRepository pollRepository;

    private final UserRepository userRepository;

    private final OptionRepository optionRepository;

    private final SecurityContextServiceImpl securityContextService;

    private final VoteMapper voteMapper;

    public VoteServiceImpl(VoteRepository voteRepository, PollRepository pollRepository, UserRepository userRepository, OptionRepository optionRepository, SecurityContextServiceImpl securityContextService, VoteMapper voteMapper) {
        this.voteRepository = voteRepository;
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
        this.optionRepository = optionRepository;
        this.securityContextService = securityContextService;
        this.voteMapper = voteMapper;
    }

    @Override
    public GetVoteResponse voting(VoteRequest voteRequest)
    {
       Poll poll = pollRepository.findPollByQuestion(voteRequest.getPollName()).
               orElseThrow(()->new PollNotExistException());

       if (!poll.isActive())
       {
           throw new PollAlreadyEndedException();
       }

       Option option = poll.getOptions().stream().filter(opt -> opt.getText().equals(voteRequest.getOptionName())).
               findFirst().orElseThrow(()->new OptionNotFoundException());


       User user = userRepository.findById(securityContextService.getCurrentUser().getId())
                .orElseThrow(() -> new UserNotFoundException());

       if (voteRepository.existsByUserAndOption_Poll(user, poll))
       {
            throw new UserAlreadyVotedException();
       }
       Vote vote = Vote.builder().option(option).user(user).build();
       user.addVote(vote);
       option.getVotes().add(vote);
       voteRepository.save(vote);

       return voteMapper.voteToGetVoteResponse(vote);
    }


    @Override
    public List<GetCountingResponse> voteCounting(String question)
    {
        Poll poll=pollRepository.findPollByQuestion(question).
                orElseThrow(()->new PollNotExistException());

        List<Option> options = optionRepository.findOptionByPoll(poll);
        List<GetCountingResponse> response = new ArrayList<>();

        for (Option option:options)
        {
            Integer count = (int) option.getVotes().stream().count();
            GetCountingResponse getCountingResponse = new GetCountingResponse(option.getText(),count);
            response.add(getCountingResponse);
        }
        return response;
    }
}
