package uk.co.jordandick.tesco.vending.states;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.InvalidInputException;
import uk.co.jordandick.tesco.vending.exception.OutOfStockException;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;

/**
 * The Class ProductSoldState.
 */
public class ProductSoldState implements VendingMachineState {

    /** The vending machine. */
    VendingMachine vendingMachine;

    /** The Constant LOGGER. */

    /**
     * Instantiates a new product sold state.
     *
     * @param vendingMachine
     *            the vending machine
     */
    public ProductSoldState(VendingMachine vendingMachine) {
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
     */
    public void insertCoin(Coin coin) throws VendingMachineException {
        throw new UnsupportedActionException();
    }

    /**
     * {@inheritDoc}
     */
    public void dispenseProduct(Product product)
        throws VendingMachineException {
        switch (product) {
            case PRODUCT_A:
                vendProductA(product);
                break;
            case PRODUCT_B:
                vendProductB(product);
                break;
            case PRODUCT_C:
                vendProductC(product);
                break;
            default:
                throw new InvalidInputException();

        }
    }

    /**
     * Vend product c.
     *
     * @param product
     *            the product
     * @throws OutOfStockException
     *             the out of stock exception
     */
    private void vendProductC(Product product) throws OutOfStockException {
        if (vendingMachine.getProductCStock() == 0) {
            throw new OutOfStockException();
        } else {
            vendingMachine
                .setProductCStock(vendingMachine.getProductCStock() - 1);
            cashUpAndReturnChange(product);
        }
    }

    /**
     * Vend product b.
     *
     * @param product
     *            the product
     * @throws OutOfStockException
     *             the out of stock exception
     */
    private void vendProductB(Product product) throws OutOfStockException {
        if (vendingMachine.getProductBStock() == 0) {
            throw new OutOfStockException();
        } else {
            vendingMachine
                .setProductBStock(vendingMachine.getProductBStock() - 1);
            cashUpAndReturnChange(product);
        }
    }

    /**
     * Vend product a.
     *
     * @param product
     *            the product
     * @throws OutOfStockException
     *             the out of stock exception
     */
    private void vendProductA(Product product) throws OutOfStockException {
        if (vendingMachine.getProductAStock() == 0) {
            throw new OutOfStockException();
        } else {
            vendingMachine
                .setProductAStock(vendingMachine.getProductAStock() - 1);
            cashUpAndReturnChange(product);
        }
    }

    /**
     * Cash up and return change.
     *
     * @param product
     *            the product
     */
    private void cashUpAndReturnChange(Product product) {
        vendingMachine.setMachineBalance(
            vendingMachine.getMachineBalance() + product.getPrice());
        vendingMachine.setCurrentCustomerBalance(0d);
        vendingMachine.setState(vendingMachine.getNoBalanceState());
    }

    /**
     * {@inheritDoc}
     */
    public void selectProduct(Product product) throws VendingMachineException {
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
        return State.PRODUCT_SOLD;
    }
}
