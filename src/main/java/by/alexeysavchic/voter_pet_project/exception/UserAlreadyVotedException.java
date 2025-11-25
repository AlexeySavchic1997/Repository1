package by.alexeysavchic.voter_pet_project.exception;

public class UserAlreadyVotedException extends RuntimeException
{
    public UserAlreadyVotedException() {
        super(ErrorMessages.userAlreadyVoted);
    }
}
