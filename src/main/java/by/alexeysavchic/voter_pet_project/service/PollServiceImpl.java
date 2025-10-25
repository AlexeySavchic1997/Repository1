package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.exceptions.OperationDeniedException;
import by.alexeysavchic.voter_pet_project.exceptions.PoolNotExistException;
import by.alexeysavchic.voter_pet_project.mappers.PollMapper;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    @Transactional
    public PollResponse createPoll(PollRequest pollRequest)
    {
        Poll poll=pollMapper.pollRequestToPoll(pollRequest);
        poll.setCreatedBy(securityContextService.getCurrentUser());
        poll=pollRepository.save(poll);

        PollResponse pollResponse=pollMapper.pollToPollResponse(poll);

        return pollResponse;
    }

    @Override
    @Transactional
    public void deletePoll (String question)
    {
        Poll poll = pollRepository.findPollByQuestion(question);
        if (poll==null)
        {
            throw new PoolNotExistException("Poll don't exist");
        }
        if (securityContextService.getCurrentUser().equals(poll.getCreatedBy())) {
            pollRepository.delete(poll);
        }
        else
        {
            throw new OperationDeniedException("Yon can't delete poll if not created it");
        }
    }



}
