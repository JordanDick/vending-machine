package uk.co.jordandick.tesco.vending;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.co.jordandick.tesco.vending.exception.InsufficientBalanceException;
import uk.co.jordandick.tesco.vending.exception.InvalidInputException;
import uk.co.jordandick.tesco.vending.exception.NoChangeException;
import uk.co.jordandick.tesco.vending.exception.OutOfStockException;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;


/**
 * The Class VendingMachineTest.
 */
public class VendingMachineTest {

    /**
     * When end to end test runs_ expect balance up and stock down.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void WhenEndToEndTestRuns_ExpectBalanceUpAndStockDown()
        throws VendingMachineException {
        VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);
        verifyOffThenTurnOn(vendingMachine);
        vendingMachine.insertCoin(Coin.POUND);
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_BALANCE);
        vendingMachine.selectProduct(Product.PRODUCT_B);
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);
        assertTrue(vendingMachine.getMachineBalance() == 11d);
        assertTrue(vendingMachine.getProductBStock() == 1);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0);
        turnOff(vendingMachine);

    }

    /**
     * When off then on and turned off again_ then machine is off.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void WhenOffThenOnAndTurnedOffAgain_ThenMachineIsOff()
        throws VendingMachineException {
        VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);

        verifyOffThenTurnOn(vendingMachine);
        turnOff(vendingMachine);
    }

    /**
     * When machine has no change_ and change is required_ throw error.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = NoChangeException.class)
    public void WhenMachineHasNoChange_AndChangeIsRequired_ThrowError()
        throws VendingMachineException {

        VendingMachine vendingMachine = new VendingMachine(0d, 3, 2, 1);
        verifyOffThenTurnOn(vendingMachine);
        vendingMachine.insertCoin(Coin.POUND);
        vendingMachine.insertCoin(Coin.TEN_PENCE);
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_BALANCE);
        vendingMachine.selectProduct(Product.PRODUCT_B);

    }

    /**
     * When machine has no change_ and no change is required_ then work as expected.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void WhenMachineHasNoChange_AndNoChangeIsRequired_ThenWorkAsExpected()
        throws VendingMachineException {

        VendingMachine vendingMachine = new VendingMachine(0d, 3, 2, 1);
        verifyOffThenTurnOn(vendingMachine);
        vendingMachine.insertCoin(Coin.POUND);

        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_BALANCE);
        vendingMachine.selectProduct(Product.PRODUCT_B);
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);
        assertTrue(vendingMachine.getMachineBalance() == 1d);
        assertTrue(vendingMachine.getProductBStock() == 1);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0);

    }

    /**
     * When not enough money inserted and product selected_ then return error.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = InsufficientBalanceException.class)
    public void whenNotEnoughMoneyInsertedAndProductSelected_ThenReturnError()
        throws VendingMachineException {
        VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);
        verifyOffThenTurnOn(vendingMachine);
        insertCoinAndVerifyState(vendingMachine, Coin.FIFTY_PENCE);
        vendingMachine.selectProduct(Product.PRODUCT_C);

    }

    /**
     * When invalid money inserted and product selected_ then return error.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = InvalidInputException.class)
    public void whenInvalidMoneyInsertedAndProductSelected_ThenReturnError()
        throws VendingMachineException {
        VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);
        verifyOffThenTurnOn(vendingMachine);
        insertCoinAndVerifyState(vendingMachine, null);

    }

    /**
     * When money inserted and invalid product selected_ then return error.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = InvalidInputException.class)
    public void whenMoneyInsertedAndInvalidProductSelected_ThenReturnError()
        throws VendingMachineException {
        VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);
        verifyOffThenTurnOn(vendingMachine);
        insertCoinAndVerifyState(vendingMachine, Coin.FIFTY_PENCE);
        vendingMachine.selectProduct(null);

    }

    /**
     * When product selected with no balance_ then return error.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenProductSelectedWithNoBalance_ThenReturnError()
        throws VendingMachineException {
        VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);
        verifyOffThenTurnOn(vendingMachine);
        vendingMachine.selectProduct(Product.PRODUCT_A);

    }

    /**
     * When end to end test runs out of stock_ error.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = OutOfStockException.class)
    public void whenEndToEndTestRunsOutOfStock_Error()
        throws VendingMachineException {
        VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);
        verifyOffThenTurnOn(vendingMachine);
        vendingMachine.insertCoin(Coin.POUND);
        vendingMachine.insertCoin(Coin.FIFTY_PENCE);
        vendingMachine.insertCoin(Coin.TWENTY_PENCE);
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_BALANCE);
        vendingMachine.selectProduct(Product.PRODUCT_C);
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);
        assertTrue(vendingMachine.getMachineBalance() == 11.7d);
        assertTrue(vendingMachine.getProductCStock() == 0);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0);

        // Product C is now out of stock
        vendingMachine.insertCoin(Coin.POUND);
        vendingMachine.insertCoin(Coin.FIFTY_PENCE);
        vendingMachine.insertCoin(Coin.TWENTY_PENCE);
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_BALANCE);
        vendingMachine.selectProduct(Product.PRODUCT_C);

    }

    /**
     * Turn off.
     *
     * @param vendingMachine the vending machine
     * @throws VendingMachineException the vending machine exception
     */
    private void turnOff(VendingMachine vendingMachine)
        throws VendingMachineException {
        vendingMachine.turnMachineOff();
        assertTrue(vendingMachine.getState().returnState() == State.OFF_STATE);
    }

    /**
     * Verify off then turn on.
     *
     * @param vendingMachine the vending machine
     * @throws VendingMachineException the vending machine exception
     */
    private void verifyOffThenTurnOn(VendingMachine vendingMachine)
        throws VendingMachineException {
        assertTrue(vendingMachine.getState().returnState() == State.OFF_STATE);
        vendingMachine.turnMachineOn();
        assertTrue(vendingMachine.getState().returnState() == State.ON_STATE);
    }

    /**
     * Insert coin and verify state.
     *
     * @param vendingMachine the vending machine
     * @param coin the coin
     * @throws VendingMachineException the vending machine exception
     */
    private void insertCoinAndVerifyState(VendingMachine vendingMachine,
        Coin coin) throws VendingMachineException {
        vendingMachine.insertCoin(coin);
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_BALANCE);
    }

}
