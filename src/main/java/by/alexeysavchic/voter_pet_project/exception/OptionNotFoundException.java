package by.alexeysavchic.voter_pet_project.exception;

public class OptionNotFoundException extends RuntimeException
{
    private final static String message="option not found";

    public OptionNotFoundException()
    {
        super(message);
    }
}
