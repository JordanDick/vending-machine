package uk.co.jordandick.tesco.machine.states;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;


/**
 * The Class OffStateTest.
 */
public class OffStateTest {
    
    /** The vending machine. */
    VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);

    /**
     * When turned off when off_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenTurnedOffWhenOff_ThenExpectException()
        throws VendingMachineException {
        assertTrue(vendingMachine.isOff());
        assertTrue(vendingMachine.getState().returnState() == State.OFF_STATE);

        vendingMachine.turnMachineOff();

    }

    /**
     * When off and turned on_ then change state and turn on.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void whenOffAndTurnedOn_ThenChangeStateAndTurnOn()
        throws VendingMachineException {
        assertTrue(vendingMachine.isOff());
        vendingMachine.turnMachineOn();
        assertFalse(vendingMachine.isOff());
        assertTrue(vendingMachine.getState().returnState() == State.ON_STATE);
    }

    /**
     * When turned off and coin inserted_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenTurnedOffAndCoinInserted_ThenExpectException()
        throws VendingMachineException {
        assertTrue(vendingMachine.isOff());
        vendingMachine.insertCoin(Coin.FIFTY_PENCE);

    }

    /**
     * When turned off and product selected_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenTurnedOffAndProductSelected_ThenExpectException()
        throws VendingMachineException {
        assertTrue(vendingMachine.isOff());
        vendingMachine.selectProduct(Product.PRODUCT_A);

    }

    /**
     * When turned off and reject coin_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenTurnedOffAndRejectCoin_ThenExpectException()
        throws VendingMachineException {
        assertTrue(vendingMachine.isOff());
        vendingMachine.refundCoin();

    }

}
