package by.alexeysavchic.voter_pet_project.exceptions;

public class PollAlreadyEndedException extends RuntimeException
{
    public PollAlreadyEndedException(String message) {
        super(message);
    }
}
