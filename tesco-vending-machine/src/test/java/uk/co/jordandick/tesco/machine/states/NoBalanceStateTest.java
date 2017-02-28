package uk.co.jordandick.tesco.machine.states;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;


/**
 * The Class NoBalanceStateTest.
 */
public class NoBalanceStateTest {

    /** The vending machine. */
    VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);

    /**
     * Before.
     */
    @Before
    public void before() {
        vendingMachine.setState(vendingMachine.getNoBalanceState());
        vendingMachine.setOff(false);
    }

    /**
     * When no balance and turned off_ then turn off.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void whenNoBalanceAndTurnedOff_ThenTurnOff()
        throws VendingMachineException {
        assertFalse(vendingMachine.isOff());
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);
        vendingMachine.turnMachineOff();
        assertTrue(vendingMachine.isOff());
        assertTrue(vendingMachine.getState().returnState() == State.OFF_STATE);

    }

    /**
     * When has no balance and product selected_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenHasNoBalanceAndProductSelected_ThenExpectException()
        throws VendingMachineException {
        vendingMachine.selectProduct(Product.PRODUCT_A);

    }

    /**
     * When has no balance and turn on_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenHasNoBalanceAndTurnOn_ThenExpectException()
        throws VendingMachineException {
        vendingMachine.turnMachineOn();

    }

    /**
     * When has no balance and refund coins selected_ then throw excepion.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenHasNoBalanceAndRefundCoinsSelected_ThenThrowExcepion()
        throws VendingMachineException {
        vendingMachine.refundCoin();

    }

    /**
     * When has no balance and coin inserted_ increment balance.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void whenHasNoBalanceAndCoinInserted_IncrementBalance()
        throws VendingMachineException {
        vendingMachine.setCurrentCustomerBalance(0.0d);

        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0d);
        vendingMachine.insertCoin(Coin.FIFTY_PENCE);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0.5d);
    }

}
