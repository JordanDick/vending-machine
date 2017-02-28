package uk.co.jordandick.tesco.vending.exception;

/**
 * The Class UnsupportedActionException.
 */
public class UnsupportedActionException extends VendingMachineException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6699967871357447551L;

    /**
     * Instantiates a new unsupported action exception.
     */
    public UnsupportedActionException() {
        super("The requested action cannot be carried out at this time");
    }

}
