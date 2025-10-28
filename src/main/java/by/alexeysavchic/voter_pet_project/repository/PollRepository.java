package by.alexeysavchic.voter_pet_project.repository;

import by.alexeysavchic.voter_pet_project.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long>
{
    public Poll findPollById(Long Id);
    public Poll findPollByQuestion(String question);
}
