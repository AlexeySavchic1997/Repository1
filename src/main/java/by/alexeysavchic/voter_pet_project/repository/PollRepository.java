package by.alexeysavchic.voter_pet_project.repository;

import by.alexeysavchic.voter_pet_project.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long>, JpaSpecificationExecutor<Poll>
{
    public Optional<Poll> findPollById(Long Id);
    public Optional<Poll> findPollByQuestion(String question);
}
