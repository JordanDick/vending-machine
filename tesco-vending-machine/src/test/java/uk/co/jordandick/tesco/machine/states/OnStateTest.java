package uk.co.jordandick.tesco.machine.states;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.InvalidInputException;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;


/**
 * The Class OnStateTest.
 */
public class OnStateTest {

    /** The vending machine. */
    VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);

    /**
     * Before.
     */
    @Before
    public void before() {
        vendingMachine.setState(vendingMachine.getOnState());
        vendingMachine.setOff(false);
    }

    /**
     * When turned off when on_ then turn off and change state.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void whenTurnedOffWhenOn_ThenTurnOffAndChangeState()
        throws VendingMachineException {
        assertFalse(vendingMachine.isOff());
        assertTrue(vendingMachine.getState().returnState() == State.ON_STATE);
        vendingMachine.turnMachineOff();
        assertTrue(vendingMachine.isOff());

    }

    /**
     * When on and coin inserted_ increment balance.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void whenOnAndCoinInserted_IncrementBalance()
        throws VendingMachineException {
        vendingMachine.setCurrentCustomerBalance(0.0d);

        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0d);
        vendingMachine.insertCoin(Coin.FIFTY_PENCE);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0.5d);
        vendingMachine.insertCoin(Coin.TEN_PENCE);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0.6d);
        vendingMachine.insertCoin(Coin.TWENTY_PENCE);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0.8d);
        vendingMachine.insertCoin(Coin.POUND);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 1.8d);
    }

    /**
     * When on and null inserted_ expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = InvalidInputException.class)
    public void whenOnAndNullInserted_ExpectException()
        throws VendingMachineException {
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 0d);
        vendingMachine.insertCoin(null);

    }

    /**
     * When turned on and product selected_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenTurnedOnAndProductSelected_ThenExpectException()
        throws VendingMachineException {
        vendingMachine.selectProduct(Product.PRODUCT_A);

    }

    /**
     * When turned on and turn on_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenTurnedOnAndTurnOn_ThenExpectException()
        throws VendingMachineException {
        vendingMachine.turnMachineOn();

    }

    /**
     * When turned on and refund coins selected_ then return coins.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void whenTurnedOnAndRefundCoinsSelected_ThenReturnCoins()
        throws VendingMachineException {
        vendingMachine.setCurrentCustomerBalance(0.8d);
        vendingMachine.refundCoin();
        assertTrue(0d == vendingMachine.getCurrentCustomerBalance());
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);
    }

}
