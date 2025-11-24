package by.alexeysavchic.voter_pet_project.exception;

public class UserAlreadyVotedException extends RuntimeException
{
    private final static String message="user already voted";

    public UserAlreadyVotedException() {
        super(message);
    }
}
