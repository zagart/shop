package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.util.Loggable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Test for order-product class.
 */
public class OrderProductTest implements Loggable {

    private Random rnd = new Random();
    private OrderProduct orderProduct = new OrderProduct();
    private Long id = rnd.nextLong();
    private Order order = new Order();
    private Product product = new Product();
    private Long quantity = (long) Math.abs(rnd.nextInt(1000));

    @Before
    public void init() {
        orderProduct.setId(id);
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);
        logger.info("OrderProduct test initialized.");
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
