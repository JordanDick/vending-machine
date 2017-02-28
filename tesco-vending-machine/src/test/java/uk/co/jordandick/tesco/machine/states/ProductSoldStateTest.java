package uk.co.jordandick.tesco.machine.states;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uk.co.jordandick.tesco.vending.VendingMachine;
import uk.co.jordandick.tesco.vending.exception.InvalidInputException;
import uk.co.jordandick.tesco.vending.exception.OutOfStockException;
import uk.co.jordandick.tesco.vending.exception.UnsupportedActionException;
import uk.co.jordandick.tesco.vending.exception.VendingMachineException;
import uk.co.jordandick.tesco.vending.model.Coin;
import uk.co.jordandick.tesco.vending.model.Product;
import uk.co.jordandick.tesco.vending.model.State;
import uk.co.jordandick.tesco.vending.states.ProductSoldState;


/**
 * The Class ProductSoldStateTest.
 */
public class ProductSoldStateTest {
    
    /** The vending machine. */
    VendingMachine vendingMachine = new VendingMachine(10d, 3, 2, 1);

    /**
     * Before.
     */
    @Before
    public void before() {
        vendingMachine.setState(vendingMachine.getProductSoldState());
        vendingMachine.setOff(false);
    }

    /**
     * When product solde and turned off_ then throw exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenProductSoldeAndTurnedOff_ThenThrowException()
        throws VendingMachineException {
        assertTrue(
            vendingMachine.getState().returnState() == State.PRODUCT_SOLD);
        vendingMachine.turnMachineOff();

    }

    /**
     * When product sold and turned on_ then throw exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenProductSoldAndTurnedOn_ThenThrowException()
        throws VendingMachineException {

        vendingMachine.turnMachineOn();

    }

    /**
     * When product sold and coin inserted_ then throw exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenProductSoldAndCoinInserted_ThenThrowException()
        throws VendingMachineException {

        vendingMachine.insertCoin(Coin.FIFTY_PENCE);

    }

    /**
     * When product sold and refund requested_ then throw exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void whenProductSoldAndRefundRequested_ThenThrowException()
        throws VendingMachineException {

        vendingMachine.refundCoin();

    }

    /**
     * When has balance and product a selected_ and no change required_ then dispense item.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void whenHasBalanceAndProductASelected_AndNoChangeRequired_ThenDispenseItem()
        throws VendingMachineException {
        vendingMachine.setState(vendingMachine.getHasBalanceState());

        vendingMachine.setCurrentCustomerBalance(0.6d);
        vendingMachine.setMachineBalance(0.2d);
        assertTrue(0.2d == vendingMachine.getMachineBalance());

        vendingMachine.selectProduct(Product.PRODUCT_A);
        assertTrue(0d == vendingMachine.getCurrentCustomerBalance());
        assertTrue(0.8d == vendingMachine.getMachineBalance());
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);

    }

    /**
     * When has balance and product b selected_ and no change required_ then dispense item.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void whenHasBalanceAndProductBSelected_AndNoChangeRequired_ThenDispenseItem()
        throws VendingMachineException {
        vendingMachine.setState(vendingMachine.getHasBalanceState());

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
     * When has balance and product c selected_ and no change required_ then dispense item.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test
    public void whenHasBalanceAndProductCSelected_AndNoChangeRequired_ThenDispenseItem()
        throws VendingMachineException {
        vendingMachine.setState(vendingMachine.getHasBalanceState());

        vendingMachine.setCurrentCustomerBalance(1.7d);
        vendingMachine.setMachineBalance(0.2d);
        assertTrue(0.2d == vendingMachine.getMachineBalance());

        vendingMachine.selectProduct(Product.PRODUCT_C);
        assertTrue(0d == vendingMachine.getCurrentCustomerBalance());
        assertTrue(1.9d == vendingMachine.getMachineBalance());
        assertTrue(
            vendingMachine.getState().returnState() == State.HAS_NO_BALANCE);

    }

    /**
     * When has balance and null selected_ the expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = InvalidInputException.class)
    public void whenHasBalanceAndNullSelected_TheExpectException()
        throws VendingMachineException {
        vendingMachine.setState(vendingMachine.getHasBalanceState());

        vendingMachine.setCurrentCustomerBalance(1.7d);
        vendingMachine.setMachineBalance(0.2d);
        assertTrue(0.2d == vendingMachine.getMachineBalance());

        vendingMachine.selectProduct(null);

    }

    /**
     * When has balance and product a selected_ and product out of stock_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = OutOfStockException.class)
    public void whenHasBalanceAndProductASelected_AndProductOutOfStock_ThenExpectException()
        throws VendingMachineException {

        vendingMachine = new VendingMachine(10d, 0, 0, 0);
        vendingMachine.setCurrentCustomerBalance(2.0d);
        vendingMachine.setState(vendingMachine.getHasBalanceState());
        vendingMachine.selectProduct(Product.PRODUCT_A);

    }

    /**
     * When has balance and product b selected_ and product out of stock_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = OutOfStockException.class)
    public void whenHasBalanceAndProductBSelected_AndProductOutOfStock_ThenExpectException()
        throws VendingMachineException {
        vendingMachine = new VendingMachine(10d, 0, 0, 0);
        vendingMachine.setCurrentCustomerBalance(2.0d);
        vendingMachine.setState(vendingMachine.getHasBalanceState());
        vendingMachine.selectProduct(Product.PRODUCT_B);
    }

    /**
     * When has balance and product c selected_ and product out of stock_ then expect exception.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = OutOfStockException.class)
    public void whenHasBalanceAndProductCSelected_AndProductOutOfStock_ThenExpectException()
        throws VendingMachineException {
        vendingMachine = new VendingMachine(10d, 0, 0, 0);
        vendingMachine.setCurrentCustomerBalance(2.0d);
        vendingMachine.setState(vendingMachine.getHasBalanceState());
        vendingMachine.selectProduct(Product.PRODUCT_C);
    }

    /**
     * Test unreachable method.
     *
     * @throws VendingMachineException the vending machine exception
     */
    @Test(expected = UnsupportedActionException.class)
    public void testUnreachableMethod() throws VendingMachineException {
        ProductSoldState pss = new ProductSoldState(vendingMachine);
        pss.selectProduct(Product.PRODUCT_A);
    }

}
