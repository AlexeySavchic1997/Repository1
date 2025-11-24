package by.alexeysavchic.voter_pet_project.exception;

public class PollAlreadyEndedException extends RuntimeException
{
    private final static String message="poll already exist";

    public PollAlreadyEndedException()
    {
        super(message);
    }
}
