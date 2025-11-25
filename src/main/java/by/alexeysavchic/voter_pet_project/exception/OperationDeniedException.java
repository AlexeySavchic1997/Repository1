package by.alexeysavchic.voter_pet_project.exception;

public class OperationDeniedException extends RuntimeException
{
    public OperationDeniedException() {
        super(ErrorMessages.operationDenied);
    }
}
