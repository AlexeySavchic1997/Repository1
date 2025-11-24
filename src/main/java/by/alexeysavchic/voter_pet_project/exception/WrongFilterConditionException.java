package by.alexeysavchic.voter_pet_project.exception;

public class WrongFilterConditionException extends RuntimeException
{
    private final static String message="wrong filter condition";

    public WrongFilterConditionException()
    {
        super(message);
    }
}
