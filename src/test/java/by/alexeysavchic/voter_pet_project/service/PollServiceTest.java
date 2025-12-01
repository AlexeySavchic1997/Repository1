package by.alexeysavchic.voter_pet_project.service;


import by.alexeysavchic.voter_pet_project.dto.request.PollRequest;
import by.alexeysavchic.voter_pet_project.entity.Poll;
import by.alexeysavchic.voter_pet_project.entity.User;
import by.alexeysavchic.voter_pet_project.exception.OperationDeniedException;
import by.alexeysavchic.voter_pet_project.exception.PollNotExistException;
import by.alexeysavchic.voter_pet_project.mapper.PollMapper;
import by.alexeysavchic.voter_pet_project.repository.PollRepository;
import by.alexeysavchic.voter_pet_project.security.SecurityContextService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PollServiceTest
{
    @Mock
    private SecurityContextService securityContextService;

    @InjectMocks
    private PollServiceImpl pollService;

    @Mock
    private PollMapper pollMapper;

    @Mock
    private PollRepository pollRepository;

    @Test
    @DisplayName("Poll create user no authenticate")
    public void pollCreateWithNoAuthUser()
    {
        PollRequest request = new PollRequest();
        when(securityContextService.getCurrentUser()).thenReturn(null);

        assertThrows(OperationDeniedException.class, () -> {
            pollService.createPoll(request);
        });
    }

    @Test
    @DisplayName("Delete poll which doesn't exist")
    public void deleteNotExistingPoll()
    {
        Poll poll=null;
        when(pollRepository.findPollById(1L)).thenReturn(Optional.ofNullable(null));

        assertThrows(PollNotExistException.class, ()->pollService.deletePoll(1L));
        verify(pollRepository, never()).delete(any(Poll.class));
    }

    @Test
    @DisplayName("Delete poll with no access")
    public void DeleteUserWithoutAccess()
    {
        User pollCreator = new User();
        pollCreator.setUsername("creator");
        User user = new User();
        user.setUsername("user");

        Poll poll = new Poll();
        poll.setId(1L);
        poll.setCreatedBy(pollCreator);

        when(pollRepository.findPollById(1L)).thenReturn(Optional.of(poll));
        when(securityContextService.getCurrentUser()).thenReturn(user);

        assertThrows(OperationDeniedException.class, ()->pollService.deletePoll(1L));

        verify(pollRepository, never()).delete(any(Poll.class));
    }
}
