package by.alexeysavchic.voter_pet_project.exceptions;

public class OperationDeniedException extends RuntimeException
{
    public OperationDeniedException(String message) {
        super(message);
    }
}
