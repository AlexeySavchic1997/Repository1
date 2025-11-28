package by.alexeysavchic.voter_pet_project.service;

import by.alexeysavchic.voter_pet_project.dto.request.GetPollsRequest;
import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.dto.response.GetPollResponse;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.OperationDeniedException;
import by.alexeysavchic.voter_pet_project.exception.PollNotExistException;
import by.alexeysavchic.voter_pet_project.mapper.PollMapper;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import by.alexeysavchic.voter_pet_project.security.Role;
import by.alexeysavchic.voter_pet_project.security.SecurityContextService;
import by.alexeysavchic.voter_pet_project.serviceInterfaces.PollService;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PollServiceImpl implements PollService
{
    private final SecurityContextService securityContextService;
    private final PollRepository pollRepository;
    private final PollMapper pollMapper;

    public PollServiceImpl(SecurityContextService securityContextService, PollRepository pollRepository, PollMapper pollMapper) {
        this.securityContextService = securityContextService;
        this.pollRepository = pollRepository;
        this.pollMapper = pollMapper;
    }

    private Specification<Poll> getIdSpecification(GetPollsRequest request)
    {
        return (root, query, criteriaBuilder) ->
        {return criteriaBuilder.equal(root.get("id"),
                request.getId());
        };
    }

    private Specification<Poll> getQuestionSpecification(GetPollsRequest request)
    {
        return (root, query, criteriaBuilder) ->
        {return criteriaBuilder.like(root.get("question"),
                "%"+request.getQuestion()+"%");
        };
    }

    private Specification<Poll> getDescriptionSpecification(GetPollsRequest request)
    {
        return (root, query, criteriaBuilder) ->
        {return criteriaBuilder.like(root.get("description"),
                "%"+request.getDescription()+"%");
        };
    }

    private Specification<Poll> getStartDateSpecification(GetPollsRequest request)
    {
        return (root, query, criteriaBuilder) ->
        {return criteriaBuilder.equal(root.get("creation_time"),
                request.getCreationTime());
        };
    }

    private Specification<Poll> getEndDateSpecification(GetPollsRequest request)
    {
        return (root, query, criteriaBuilder) ->
        {return criteriaBuilder.equal(root.get("ending_time"),
                request.getEndingTime());
        };
    }

    private Specification<Poll> getPollCreatorSpecification(GetPollsRequest request)
    {
        return (root, query, criteriaBuilder) ->
        {
            Join<User, Poll> creatorName = root.join("users");
            return criteriaBuilder.like(creatorName.get("username"), "%"+request.getUsername()+"%");
        };
    }

    @Override
    public List<GetPollResponse> getPolls(GetPollsRequest request)
    {
        Specification<Poll> specification=null;
        if (request.getId()!=null)
        {
            specification=specification.and(getIdSpecification(request));
        }
        if (request.getQuestion()!=null)
        {
            specification=specification.and(getQuestionSpecification(request));
        }
        if (request.getDescription()!=null)
        {
            specification=specification.and(getDescriptionSpecification(request));
        }
        if (request.getCreationTime()!=null)
        {
            specification=specification.and(getStartDateSpecification(request));
        }
        if (request.getEndingTime()!=null)
        {
            specification=specification.and(getEndDateSpecification(request));
        }
        if (request.getUsername()!=null)
        {
            specification=specification.and(getPollCreatorSpecification(request));
        }
        if(specification==null)
        {
           return pollMapper.ListPollToListGetPollResponse(pollRepository.findAll());
        }
        else
        {
           return pollMapper.ListPollToListGetPollResponse(pollRepository.findAll(specification));
        }
    }

    @Override
    public GetPollResponse createPoll(PollRequest pollRequest)
    {
        User currentUser = securityContextService.getCurrentUser();
        if (currentUser == null)
        {
            throw new OperationDeniedException();
        }
        Poll poll=pollMapper.pollRequestToPoll(pollRequest);
        poll.setCreatedBy(securityContextService.getCurrentUser());
        poll=pollRepository.save(poll);

        return pollMapper.pollToGetPollResponse(poll);
    }

    @Override
    public void deletePoll (Long id)
    {
        Poll poll = pollRepository.findPollById(id).orElseThrow(()->new PollNotExistException());

        if ((securityContextService.getCurrentUser().equals(poll.getCreatedBy()))||
                (securityContextService.getCurrentUser().hasRole(Role.ROLE_ADMIN))||
        securityContextService.getCurrentUser().hasRole(Role.ROLE_MODERATOR))
        {
            pollRepository.delete(poll);
        }
        else
        {
            throw new OperationDeniedException();
        }
    }

}
