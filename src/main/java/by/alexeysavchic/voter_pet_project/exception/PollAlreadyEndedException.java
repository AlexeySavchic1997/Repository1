package by.alexeysavchic.voter_pet_project.exception;

public class PollAlreadyEndedException extends RuntimeException
{
    public PollAlreadyEndedException()
    {
        super(ErrorMessages.pollAlreadyEnded);
    }
}
