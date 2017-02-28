package uk.co.jordandick.tesco.vending;

import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.states.HasBalanceState;
import uk.co.jordandick.tesco.vending.states.NoBalanceState;
import uk.co.jordandick.tesco.vending.states.OffState;
import uk.co.jordandick.tesco.vending.states.OnState;
import uk.co.jordandick.tesco.vending.states.ProductSoldState;
import uk.co.jordandick.tesco.vending.states.VendingMachineState;

/**
 * The Class VendingMachine.
 */
public class VendingMachine {

    /** The off state. */
    private VendingMachineState offState;

    /** The on state. */
    private VendingMachineState onState;

    /** The has balance state. */
    private VendingMachineState hasBalanceState;

    /** The product sold state. */
    private VendingMachineState productSoldState;

    /** The no balance state. */
    private VendingMachineState noBalanceState;

    /** The state. */
    private VendingMachineState state;

    /** The is off. */
    private boolean isOff = true;

    /** The machine balance. */
    private double machineBalance = 0;

    /** The product a stock. */
    private int productAStock = 0;

    /** The product b stock. */
    private int productBStock = 0;

    /** The product c stock. */
    private int productCStock = 0;

    /** The current customer balance. */
    private double currentCustomerBalance = 0;

    /**
     * Instantiates a new vending machine.
     *
     * @param startingBalance
     *            the starting balance
     * @param productAStock
     *            the product a stock
     * @param productBStock
     *            the product b stock
     * @param ProductCStock
     *            the product c stock
     */
    public VendingMachine(double startingBalance, int productAStock,
        int productBStock, int productCStock) {

        offState = new OffState(this);
        onState = new OnState(this);
        hasBalanceState = new HasBalanceState(this);
        productSoldState = new ProductSoldState(this);
        noBalanceState = new NoBalanceState(this);
        state = offState;
        this.machineBalance = startingBalance;
        this.productAStock = productAStock;
        this.productBStock = productBStock;
        this.productCStock = productCStock;

    }

    /**
     * Insert coin.
     *
     * @param coin
     *            the coin
     * @throws VendingMachineException
     *             the vending machine exception
     */
    public void insertCoin(Coin coin) throws VendingMachineException {
        state.insertCoin(coin);
    }

    /**
     * Refund coin.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    public void refundCoin() throws VendingMachineException {
        state.refundCoins();

    }

    /**
     * Turn machine on.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    public void turnMachineOn() throws VendingMachineException {
        state.turnMachineOn();
    }

    /**
     * Turn machine off.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    public void turnMachineOff() throws VendingMachineException {
        state.turnMachineOff();
    }

    /**
     * Select product.
     *
     * @param product
     *            the product
     * @throws VendingMachineException
     *             the vending machine exception
     */
    public void selectProduct(Product product) throws VendingMachineException {
        state.selectProduct(product);
        state.dispenseProduct(product);

    }

    /**
     * Gets the machine balance.
     *
     * @return the startingBalance
     */
    public double getMachineBalance() {
        return machineBalance;
    }

    /**
     * Sets the machine balance.
     *
     * @param startingBalance
     *            the startingBalance to set
     */
    public void setMachineBalance(double machineBalance) {
        this.machineBalance = machineBalance;
    }

    /**
     * Gets the product a stock.
     *
     * @return the productAStock
     */
    public int getProductAStock() {
        return productAStock;
    }

    /**
     * Sets the product a stock.
     *
     * @param productAStock
     *            the productAStock to set
     */
    public void setProductAStock(int productAStock) {
        this.productAStock = productAStock;
    }

    /**
     * Gets the product b stock.
     *
     * @return the productBStock
     */
    public int getProductBStock() {
        return productBStock;
    }

    /**
     * Sets the product b stock.
     *
     * @param productBStock
     *            the productBStock to set
     */
    public void setProductBStock(int productBStock) {
        this.productBStock = productBStock;
    }

    /**
     * Gets the product c stock.
     *
     * @return the productCStock
     */
    public int getProductCStock() {
        return productCStock;
    }

    /**
     * Sets the product c stock.
     *
     * @param productCStock
     *            the productCStock to set
     */
    public void setProductCStock(int productCStock) {
        this.productCStock = productCStock;
    }

    /**
     * Gets the current customer balance.
     *
     * @return the currentCustomerBalance
     */
    public double getCurrentCustomerBalance() {
        return currentCustomerBalance;
    }

    /**
     * Sets the current customer balance.
     *
     * @param currentCustomerBalance
     *            the currentCustomerBalance to set
     */
    public void setCurrentCustomerBalance(double currentCustomerBalance) {
        this.currentCustomerBalance = currentCustomerBalance;
    }

    /**
     * Gets the off state.
     *
     * @return the offState
     */
    public VendingMachineState getOffState() {
        return offState;
    }

    /**
     * Gets the on state.
     *
     * @return the onState
     */
    public VendingMachineState getOnState() {
        return onState;
    }

    /**
     * Gets the checks for balance state.
     *
     * @return the insertingCoinsState
     */
    public VendingMachineState getHasBalanceState() {
        return hasBalanceState;
    }

    /**
     * Gets the product sold state.
     *
     * @return the dispensingProductState
     */
    public VendingMachineState getProductSoldState() {
        return productSoldState;
    }

    /**
     * Gets the no balance state.
     *
     * @return the refundingCoinsState
     */
    public VendingMachineState getNoBalanceState() {
        return noBalanceState;
    }

    /**
     * Checks if is off.
     *
     * @return the isOff
     */
    public boolean isOff() {
        return isOff;
    }

    /**
     * Sets the off.
     *
     * @param isOff
     *            the isOff to set
     */
    public void setOff(boolean isOff) {
        this.isOff = isOff;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public VendingMachineState getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state
     *            the state to set
     */
    public void setState(VendingMachineState state) {
        this.state = state;
    }
}
