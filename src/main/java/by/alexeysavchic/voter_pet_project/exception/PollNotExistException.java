package by.alexeysavchic.voter_pet_project.exception;

public class PollNotExistException extends RuntimeException
{
    public PollNotExistException() {
        super(ErrorMessages.pollNotExist);
    }
}
