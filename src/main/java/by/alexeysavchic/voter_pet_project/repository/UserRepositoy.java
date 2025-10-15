package by.alexeysavchic.voter_pet_project.repository;

import by.alexeysavchic.voter_pet_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoy extends JpaRepository<User, Long>
{

}
