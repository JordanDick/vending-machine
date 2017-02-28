package uk.co.jordandick.tesco.vending.exception;

public class InvalidInputException extends VendingMachineException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8585783284772345613L;

    /**
     * Instantiates a new insufficient balance exception.
     */
    public InvalidInputException() {
        super("Invalid Input detected");

    }

}