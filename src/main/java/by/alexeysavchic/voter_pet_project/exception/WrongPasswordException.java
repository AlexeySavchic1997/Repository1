package by.alexeysavchic.voter_pet_project.exception;

public class WrongPasswordException extends RuntimeException
{
    public WrongPasswordException()
    {
        super(ErrorMessages.wrongPasswordException);
    }
}
