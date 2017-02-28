package uk.co.jordandick.tesco.vending.states;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;

/**
 * The Class OffState.
 */
public class OffState implements VendingMachineState {

    /** The vending machine. */
    VendingMachine vendingMachine;

    /** The Constant LOGGER. */

    /**
     * Instantiates a new off state.
     *
     * @param vendingMachine
     *            the vending machine
     */
    public OffState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    /**
     * {@inheritDoc}
     */
    public void turnMachineOff() throws VendingMachineException {
        throw new UnsupportedActionException();
    }

    /**
     * {@inheritDoc}
     */
    public void turnMachineOn() {
        vendingMachine.setOff(false);
        vendingMachine.setState(vendingMachine.getOnState());

    }

    /**
     * {@inheritDoc}
     */
    public void insertCoin(Coin coin) throws VendingMachineException {
        throw new UnsupportedActionException();
    }

    /**
     * {@inheritDoc}
     */
    public void selectProduct(Product product) throws VendingMachineException {
    }

    /**
     * {@inheritDoc}
     */
    public void dispenseProduct(Product product)
        throws VendingMachineException {
        throw new UnsupportedActionException();
    }

    /**
     * {@inheritDoc}
     */
    public void refundCoins() throws VendingMachineException {
        throw new UnsupportedActionException();
    }

    /**
     * {@inheritDoc}
     */
    public State returnState() {
        return State.OFF_STATE;
    }
}
