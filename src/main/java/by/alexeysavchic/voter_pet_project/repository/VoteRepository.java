package by.alexeysavchic.voter_pet_project.repository;

import by.alexeysavchic.voter_pet_project.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>
{
}
