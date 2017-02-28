package uk.co.jordandick.tesco.vending.exception;

/**
 * The Class OutOfStockException.
 */
public class OutOfStockException extends VendingMachineException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8585783284772345613L;

    /**
     * Instantiates a new out of stock exception.
     */
    public OutOfStockException() {
        super(
            "This Machine currently has Insufficeient stock to complete this transaction");

    }

}