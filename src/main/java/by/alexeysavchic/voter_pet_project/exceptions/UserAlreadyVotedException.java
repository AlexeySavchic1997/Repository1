package by.alexeysavchic.voter_pet_project.exceptions;

public class UserAlreadyVotedException extends RuntimeException
{
    public UserAlreadyVotedException(String message) {
        super(message);
    }
}
