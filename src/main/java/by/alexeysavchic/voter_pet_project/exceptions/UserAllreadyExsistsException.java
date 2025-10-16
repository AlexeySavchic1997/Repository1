package by.alexeysavchic.voter_pet_project.exceptions;

public class UserAllreadyExsistsException extends RuntimeException
{
    public UserAllreadyExsistsException(String message) {
        super(message);
    }
}
