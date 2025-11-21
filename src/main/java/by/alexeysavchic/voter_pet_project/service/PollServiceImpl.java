package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.FilterPollRequset;
import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.AllPollsResponse;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.OperationDeniedException;
import by.alexeysavchic.voter_pet_project.exception.PollNotExistException;
import by.alexeysavchic.voter_pet_project.exception.WrongFilterConditionException;
import by.alexeysavchic.voter_pet_project.mapper.PollMapper;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class PollServiceImpl implements PollService
{
    private final SecurityContextService securityContextService;
    private final PollRepository pollRepository;
    private final PollMapper pollMapper;

    public PollServiceImpl(SecurityContextService securityContextService, PollRepository pollRepository, PollMapper pollMapper)
    {
        this.securityContextService = securityContextService;
        this.pollRepository = pollRepository;
        this.pollMapper = pollMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AllPollsResponse> getPolls(FilterPollRequset filter, String condition, LocalDate dateCondition)
    {
        if (((filter==FilterPollRequset.CREATED_BY || filter==FilterPollRequset.QUESTION || filter==FilterPollRequset.DESCRIPTION)
        && condition==null) ||
                ((filter==FilterPollRequset.CREATING_TIME || filter==FilterPollRequset.ENDING_TIME) && dateCondition==null))
        {
            throw new WrongFilterConditionException("wrong filter condition");
        }
        List<AllPollsResponse> response = new ArrayList<>();
        List<Poll> polls= pollRepository.findAll();
        switch (filter)
        {
            case QUESTION:
                polls=polls.stream().filter(poll -> poll.getQuestion().contains(condition)).toList();
                break;
            case DESCRIPTION:
                polls=polls.stream().filter(poll -> poll.getDescription().contains(condition)).toList();
                break;
            case CREATED_BY:
                polls=polls.stream().filter(poll -> poll.getCreatedBy().getUsername().contains(condition)).toList();
                break;
            case CREATING_TIME:
                polls=polls.stream().filter(poll -> poll.getCreationTime().toLocalDate().equals(dateCondition)).toList();
                break;
            case ENDING_TIME:
                polls=polls.stream().filter(poll -> poll.getEndingTime().toLocalDate().equals(dateCondition)).toList();
                break;
            default:
                break;
        }
        for (Poll poll:polls)
        {
            response.add(pollMapper.pollToAllPollsResponse(poll));
        }
        return response;
    }

    @Override
    @Transactional
    public PollResponse createPoll(PollRequest pollRequest)
    {
        User currentUser = securityContextService.getCurrentUser();
        if (currentUser == null) {
            throw new OperationDeniedException("User not authenticated");
        }
        Poll poll=pollMapper.pollRequestToPoll(pollRequest);
        poll.setCreatedBy(securityContextService.getCurrentUser());
        poll=pollRepository.save(poll);

        PollResponse pollResponse=pollMapper.pollToPollResponse(poll);

        return pollResponse;
    }

    @Override
    @Transactional
    public void deletePoll (Long id)
    {
        Poll poll = pollRepository.findPollById(id);
        if (poll==null)
        {
            throw new PollNotExistException("Poll don't exist");
        }
        if ((securityContextService.getCurrentUser().equals(poll.getCreatedBy()))||
                (securityContextService.getCurrentUser().hasRole(Role.ROLE_ADMIN))||
        securityContextService.getCurrentUser().hasRole(Role.ROLE_MODERATOR))
        {
            pollRepository.delete(poll);
        }
        else
        {
            throw new OperationDeniedException("Operation Denied");
        }
    }

}
