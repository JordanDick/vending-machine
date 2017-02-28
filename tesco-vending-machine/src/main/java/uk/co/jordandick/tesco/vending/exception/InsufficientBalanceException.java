package uk.co.jordandick.tesco.vending.exception;

/**
 * The Class InsufficientBalanceException.
 */
public class InsufficientBalanceException extends VendingMachineException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8585783284772345613L;

    /**
     * Instantiates a new insufficient balance exception.
     */
    public InsufficientBalanceException() {
        super("Insufficient Balance present for the requested product");

    }

}
