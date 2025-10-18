package by.alexeysavchic.voter_pet_project.exceptions;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message) {
        super(message);
    }
}
