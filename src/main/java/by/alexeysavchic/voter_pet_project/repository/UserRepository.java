package by.alexeysavchic.voter_pet_project.repository;

import by.alexeysavchic.voter_pet_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
public Optional<User> findUserByUsername(String username);
public Optional<User> findUserById(Long id);
public boolean existsByUsername(String username);
public boolean existsByEmail(String email);
}
