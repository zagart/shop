package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.interfaces.Loggable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Test for order-product class.
 */
public class OrderProductTest implements Loggable {

    private Random rnd = new Random();
    private OrderProduct1 orderProduct = new OrderProduct1();
    private Long id = rnd.nextLong();
    private Order1 order = new Order1();
    private Product1 product = new Product1();
    private Long quantity = (long) Math.abs(rnd.nextInt(1000));

    @Before
    public void init() {
        orderProduct.setId(id);
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);
        logger.info("OrderProduct1 test initialized.");
    }

    @Test
    public void getIdTest() {
        Assert.assertEquals(id,orderProduct.getId());
    }

    @Test
    public void getOrderTest() {
        Assert.assertEquals(order,orderProduct.getOrder());
    }

    @Test
    public void getProductTest() {
        Assert.assertEquals(product,orderProduct.getProduct());
    }

    @Test
    public void getQuantityTest() {
        Assert.assertEquals(quantity,orderProduct.getQuantity());
    }

}
