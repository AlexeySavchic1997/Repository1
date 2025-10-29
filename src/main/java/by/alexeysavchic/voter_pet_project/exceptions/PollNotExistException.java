package by.alexeysavchic.voter_pet_project.exceptions;

public class PollNotExistException extends RuntimeException
{
    public PollNotExistException(String message) {
        super(message);
    }
}
