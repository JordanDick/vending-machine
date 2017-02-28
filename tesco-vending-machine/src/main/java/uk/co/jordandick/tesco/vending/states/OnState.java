package uk.co.jordandick.tesco.vending.states;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.InvalidInputException;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;

/**
 * The Class OnState.
 */
public class OnState implements VendingMachineState {

    /** The vending machine. */
    VendingMachine vendingMachine;

    /** The Constant LOGGER. */

    /**
     * Instantiates a new on state.
     *
     * @param vendingMachine
     *            the vending machine
     */
    public OnState(VendingMachine vendingMachine) {
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
     * 
     * @throws VendingMachineException
     */
    public void insertCoin(Coin coin) throws VendingMachineException {
        if (coin == null) {
            throw new InvalidInputException();
        }
        vendingMachine.setCurrentCustomerBalance(
            vendingMachine.getCurrentCustomerBalance() + coin.getValue());

        vendingMachine.setState(vendingMachine.getHasBalanceState());

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
    public void selectProduct(Product product) throws VendingMachineException {
    }

    /**
     * {@inheritDoc}
     */
    public void refundCoins() {
        double beforeRefund = vendingMachine.getCurrentCustomerBalance();
        vendingMachine.setCurrentCustomerBalance(0);

        vendingMachine.setState(vendingMachine.getNoBalanceState());
    }

    /**
     * {@inheritDoc}
     */
    public State returnState() {
        return State.ON_STATE;
    }
}
