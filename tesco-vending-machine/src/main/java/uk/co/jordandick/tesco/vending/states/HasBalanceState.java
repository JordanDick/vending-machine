package uk.co.jordandick.tesco.vending.states;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.InsufficientBalanceException;
import uk.co.jordandick.tesco.vending.exception.InvalidInputException;
import uk.co.jordandick.tesco.vending.exception.NoChangeException;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;

/**
 * The Class HasBalanceState.
 */
public class HasBalanceState implements VendingMachineState {

    /** The vending machine. */
    VendingMachine vendingMachine;

    /**
     * Instantiates a new checks for balance state.
     *
     * @param vendingMachine
     *            the vending machine
     */
    public HasBalanceState(VendingMachine vendingMachine) {
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
    public void selectProduct(Product product) throws VendingMachineException {
        if (product == null) {
            throw new InvalidInputException();
        }
        if (vendingMachine.getCurrentCustomerBalance() >= product.getPrice()) {
            if ((vendingMachine.getCurrentCustomerBalance()
                - product.getPrice()) > vendingMachine.getMachineBalance()) {
                throw new NoChangeException();
            } else {
                vendingMachine.setState(vendingMachine.getProductSoldState());
            }
        } else {
            throw new InsufficientBalanceException();

        }
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
    public void refundCoins() {
        double beforeRefund = vendingMachine.getCurrentCustomerBalance();
        vendingMachine.setCurrentCustomerBalance(0);

        vendingMachine.setState(vendingMachine.getNoBalanceState());

    }

    /**
     * {@inheritDoc}
     */
    public State returnState() {
        return State.HAS_BALANCE;
    }
}
