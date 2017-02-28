package uk.co.jordandick.tesco.vending.states;

import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;

/**
 * The Interface VendingMachineState.
 */
public interface VendingMachineState {

    /**
     * Turn machine off.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    void turnMachineOff() throws VendingMachineException;

    /**
     * Turn machine on.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    void turnMachineOn() throws VendingMachineException;

    /**
     * Insert coin.
     *
     * @param coin
     *            the coin
     * @throws VendingMachineException
     *             the vending machine exception
     */
    void insertCoin(Coin coin) throws VendingMachineException;

    /**
     * Select product.
     *
     * @param product
     *            the product
     * @throws VendingMachineException
     *             the vending machine exception
     */
    void selectProduct(Product product) throws VendingMachineException;

    /**
     * Dispense product.
     *
     * @param product
     *            the product
     * @throws VendingMachineException
     *             the vending machine exception
     */
    void dispenseProduct(Product product) throws VendingMachineException;

    /**
     * Refund coins.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    void refundCoins() throws VendingMachineException;

    /**
     * Return state.
     *
     * @return the state
     */
    State returnState();
}
