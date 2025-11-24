package by.alexeysavchic.voter_pet_project.exception;

public class UserNotFoundException extends RuntimeException
{
    private final static String message="user not found";

    public UserNotFoundException() {
        super(message);
    }
}
