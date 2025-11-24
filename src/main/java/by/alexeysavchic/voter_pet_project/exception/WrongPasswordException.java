package by.alexeysavchic.voter_pet_project.exception;

public class WrongPasswordException extends RuntimeException
{
    private final static String message="wrong password";

    public WrongPasswordException()
    {
        super(message);
    }
}
