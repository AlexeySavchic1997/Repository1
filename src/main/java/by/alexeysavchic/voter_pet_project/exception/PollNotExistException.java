package by.alexeysavchic.voter_pet_project.exception;

public class PollNotExistException extends RuntimeException
{
    private final static String message="poll doesn't exist";

    public PollNotExistException() {
        super(message);
    }
}
