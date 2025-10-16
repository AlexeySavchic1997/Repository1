package by.alexeysavchic.voter_pet_project.exceptions;

public class WrongPasswordException extends RuntimeException
{
    public WrongPasswordException(String message) {
        super(message);
    }
}
