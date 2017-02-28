package uk.co.jordandick.tesco.machine.states;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.InsufficientBalanceException;
import uk.co.jordandick.tesco.vending.exception.InvalidInputException;
import uk.co.jordandick.tesco.vending.exception.NoChangeException;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;
import uk.co.jordandick.tesco.vending.states.HasBalanceState;


/**
 * The Class HasBalanceStateTest.
 */
public class HasBalanceStateTest {

    /** The vending machine. */
    VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);

    /**
     * Before.
     */
    @Before
    public void before() {
        vendingMachine.setState(vendingMachine.getHasBalanceState());
        vendingMachine.setOff(false);
    }

    /**
     * When has balance and turned off_ then throw exception.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenHasBalanceAndTurnedOff_ThenThrowException()
        throws VendingMachineException {
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_BALANCE);
        vendingMachine.turnMachineOff();

    }

    /**
     * When has balance and turned on_ then throw exception.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenHasBalanceAndTurnedOn_ThenThrowException()
        throws VendingMachineException {
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_BALANCE);
        vendingMachine.turnMachineOn();

    }

    /**
     * When has balance and coin inserted_ increment balance.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test
    public void whenHasBalanceAndCoinInserted_IncrementBalance()
        throws VendingMachineException {
        vendingMachine.setCurrentCustomerBalance(1.0d);

        assertTrue(vendingMachine.getCurrentCustomerBalance() == 1d);
        vendingMachine.insertCoin(Coin.FIFTY_PENCE);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 1.5d);
        vendingMachine.insertCoin(Coin.TEN_PENCE);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 1.6d);
        vendingMachine.insertCoin(Coin.TWENTY_PENCE);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 1.8d);
        vendingMachine.insertCoin(Coin.POUND);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 2.8d);
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
     * When has balance and product selected_ but no change_ then expect exception.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test(expected = NoChangeException.class)
    public void whenHasBalanceAndProductSelected_ButNoChange_ThenExpectException()
        throws VendingMachineException {
        vendingMachine.setCurrentCustomerBalance(1.3d);
        vendingMachine.setMachineBalance(0.2);
        vendingMachine.selectProduct(Product.PRODUCT_B);

    }

    /**
     * When has balance and product selected_ but product greater than balance_ then expect exception.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test(expected = InsufficientBalanceException.class)
    public void whenHasBalanceAndProductSelected_ButProductGreaterThanBalance_ThenExpectException()
        throws VendingMachineException {
        vendingMachine.setCurrentCustomerBalance(1.3d);
        vendingMachine.setMachineBalance(0.2d);
        vendingMachine.selectProduct(Product.PRODUCT_C);

    }

    /**
     * When has balance and product selected_ and no change required_ then dispense item.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test
    public void whenHasBalanceAndProductSelected_AndNoChangeRequired_ThenDispenseItem()
        throws VendingMachineException {
        vendingMachine.setCurrentCustomerBalance(1.0d);
        vendingMachine.setMachineBalance(0.2d);
        assertTrue(0.2d == vendingMachine.getMachineBalance());

        vendingMachine.selectProduct(Product.PRODUCT_B);
        assertTrue(0d == vendingMachine.getCurrentCustomerBalance());
        assertTrue(1.2d == vendingMachine.getMachineBalance());
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);

    }

    /**
     * When has balance and product selected_ and change required_ then dispense item return change.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test
    public void whenHasBalanceAndProductSelected_AndChangeRequired_ThenDispenseItemReturnChange()

        throws VendingMachineException {

        vendingMachine.setCurrentCustomerBalance(1.2d);
        vendingMachine.setMachineBalance(0.2d);
        assertTrue(0.2d == vendingMachine.getMachineBalance());

        vendingMachine.selectProduct(Product.PRODUCT_B);
        assertTrue(0d == vendingMachine.getCurrentCustomerBalance());
        assertTrue(1.2d == vendingMachine.getMachineBalance());
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);
        assertTrue(0d == vendingMachine.getCurrentCustomerBalance());

    }

    /**
     * When has balance and null inserted_ expect exception.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test(expected = InvalidInputException.class)
    public void whenHasBalanceAndNullInserted_ExpectException()
        throws VendingMachineException {
        vendingMachine.setCurrentCustomerBalance(1.0d);
        assertTrue(vendingMachine.getCurrentCustomerBalance() == 1.0d);
        vendingMachine.insertCoin(null);

    }

    /**
     * When has balance and refund coins selected_ then return coins.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test
    public void whenHasBalanceAndRefundCoinsSelected_ThenReturnCoins()
        throws VendingMachineException {
        vendingMachine.setCurrentCustomerBalance(0.8d);
        vendingMachine.refundCoin();
        assertTrue(0d == vendingMachine.getCurrentCustomerBalance());
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);
    }

    /**
     * Test unreachable method.
     *
     * @throws VendingMachineException
     *             the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void testUnreachableMethod() throws VendingMachineException {
        HasBalanceState hbs = new HasBalanceState(vendingMachine);
        hbs.dispenseProduct(Product.PRODUCT_A);
    }

}
