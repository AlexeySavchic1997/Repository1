package by.alexeysavchic.voter_pet_project.exception;

public class OperationDeniedException extends RuntimeException
{
    private final static String message="Operation denied";

    public OperationDeniedException() {
        super(message);
    }
}
