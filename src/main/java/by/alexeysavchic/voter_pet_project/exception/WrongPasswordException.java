package by.alexeysavchic.voter_pet_project.exception;

public class WrongPasswordException extends RuntimeException
{
    public WrongPasswordException(String message) {
        super(message);
    }
}
