package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.mappers.PollMapper;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import by.alexeysavchic.voter_pet_project.repository.UserRepositoy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PollServiceImpl implements PollService
{
    UserRepositoy userRepositoy;
    PollRepository pollRepository;
    PollMapper pollMapper;

    public PollServiceImpl(UserRepositoy userRepositoy, PollRepository pollRepository, PollMapper pollMapper) {
        this.userRepositoy = userRepositoy;
        this.pollRepository = pollRepository;
        this.pollMapper = pollMapper;
    }

    @Override
    @Transactional
    public PollResponse createPoll(PollRequest pollRequest, long id, int duration)
    {
        Poll poll=pollMapper.pollRequestToPoll(pollRequest, id);
        LocalDateTime creationTime=LocalDateTime.now();
        poll.setCreationTime(creationTime);
        poll.setEndingTime(creationTime.plusDays(duration));
        poll=pollRepository.save(poll);

        PollResponse pollResponse=pollMapper.pollToPollResponse(poll);

        return pollResponse;
    }
}
