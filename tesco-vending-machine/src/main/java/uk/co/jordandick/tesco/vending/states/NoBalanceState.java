package uk.co.jordandick.tesco.vending.states;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;

/**
 * The Class NoBalanceState.
 */
public class NoBalanceState implements VendingMachineState {

    /** The vending machine. */
    VendingMachine vendingMachine;

    /**
     * Instantiates a new no balance state.
     *
     * @param vendingMachine
     *            the vending machine
     */
    public NoBalanceState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    /**
     * {@inheritDoc}
     */
    public void turnMachineOff() {
        vendingMachine.setOff(true);
        vendingMachine.setState(vendingMachine.getOffState());
    }

    /**
     * {@inheritDoc}
     */
    public void turnMachineOn() throws VendingMachineException {
        throw new UnsupportedActionException();
    }

    /**
     * {@inheritDoc}
     */
    public void insertCoin(Coin coin) {
        vendingMachine.setCurrentCustomerBalance(
            vendingMachine.getCurrentCustomerBalance() + coin.getValue());

        vendingMachine.setState(vendingMachine.getHasBalanceState());

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
        return State.HAS_NO_BALANCE;
    }
}
