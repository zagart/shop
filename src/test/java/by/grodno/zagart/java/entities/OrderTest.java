package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.util.Loggable;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static by.grodno.zagart.java.util.CommonUtil.randomOrderProductList;

/**
 * Test for order class.
 */
public class OrderTest implements Loggable {

    private Random rnd = new Random();
    private Order order = new Order();
    private Long id = rnd.nextLong();
    private String number = RandomStringUtils.randomNumeric(20);
    private Date dateOfOrder = new Date();
    private List<OrderProduct> orderProducts = randomOrderProductList((long) rnd.nextInt(100));

    @Before
    public void init() {
        order.setId(id);
        order.setNumber(number);
        order.setDateOfOrder(dateOfOrder);
        order.setOrderProduct(orderProducts);
        logger.info("Order test initialized.");
    }

    @Test
    public void getIdTest() {
        Assert.assertEquals(id,order.getId());
    }

    @Test
    public void getNumberTest() {
        Assert.assertEquals(number,order.getNumber());
    }

    @Test
    public void getDateOfOrderTest() {
        Assert.assertEquals(dateOfOrder,order.getDateOfOrder());
    }

    @Test
    public void getOrderProductTest() {
        Assert.assertEquals(orderProducts,order.getOrderProduct());
    }

}
