package by.alexeysavchic.voter_pet_project.exception;

public class OptionNotFoundException extends RuntimeException
{
    public OptionNotFoundException()
    {
        super(ErrorMessages.optionNotFound);
    }
}
