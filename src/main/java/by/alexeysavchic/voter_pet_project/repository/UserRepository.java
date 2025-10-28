package by.alexeysavchic.voter_pet_project.repository;

import by.alexeysavchic.voter_pet_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
public User findUserByUsername(String username);
public User findUserByEmail(String email);

public User findUserById(Long id);
}
