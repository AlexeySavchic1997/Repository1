package by.alexeysavchic.voter_pet_project.exception;

public class WrongFilterConditionException extends RuntimeException
{
    public WrongFilterConditionException()
    {
        super(ErrorMessages.wrongFilterCondition);
    }
}
