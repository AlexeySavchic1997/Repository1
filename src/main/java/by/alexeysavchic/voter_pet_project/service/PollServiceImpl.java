package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.AllPollsResponse;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exceptions.OperationDeniedException;
import by.alexeysavchic.voter_pet_project.exceptions.PollNotExistException;
import by.alexeysavchic.voter_pet_project.mappers.PollMapper;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<AllPollsResponse> getAllPolls()
    {
        List<AllPollsResponse> response = new ArrayList<>();
        List<Poll> polls= pollRepository.findAll();
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
