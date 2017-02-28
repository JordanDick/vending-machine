package uk.co.jordandick.tesco.vending.exception;

/**
 * The Class NoChangeException.
 */
public class NoChangeException extends VendingMachineException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8585783284772345613L;

    /**
     * Instantiates a new no change exception.
     */
    public NoChangeException() {
        super(
            "This Machine currently has Insufficeient change to complete this transaction");

    }

}