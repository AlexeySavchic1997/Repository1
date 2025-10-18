package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.PollRequset;
import by.alexeysavchic.voter_pet_project.dto.response.PollResponse;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import by.alexeysavchic.voter_pet_project.repository.UserRepositoy;
import org.springframework.transaction.annotation.Transactional;

public class PollServiceImpl implements PollService
{
    UserRepositoy userRepositoy;
    PollRepository pollRepository;

    public PollServiceImpl(UserRepositoy userRepositoy, PollRepository pollRepository) {
        this.userRepositoy = userRepositoy;
        this.pollRepository = pollRepository;
    }

    @Override
    @Transactional
    public PollResponse createPoll(PollRequset pollRequset, long id)
    {
        Poll poll = new Poll();


        return null;
    }
}
