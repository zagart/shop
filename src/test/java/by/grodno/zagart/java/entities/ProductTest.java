package by.grodno.zagart.java.entities;

import by.grodno.zagart.java.Entities.OrderProduct;
import by.grodno.zagart.java.Entities.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by intelisoft on 13.07.2016.
 */

public class ProductTest {

    private static Random rnd = new Random();
    private Product product = new Product();
    private static Long id = rnd.nextLong();
    private static String name = RandomStringUtils.randomAlphabetic(100);
    private static String description = RandomStringUtils.randomAlphabetic(100);
    private static Long cost = rnd.nextLong();
    private static List<OrderProduct> orderProducts = randomOrderProductList((long) rnd.nextInt(100));

    @Before
    public void init() {
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setCost(cost);
        product.setOrderProduct(orderProducts);
    }

    @Test
    public void getIdTest() {
        Assert.assertEquals(id, product.getId());
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals(name, product.getName());
    }

    @Test
    public void getDescriptionTest() {
        Assert.assertEquals(description, product.getDescription());
    }

    @Test
    public void getCostTest() {
        Assert.assertEquals(cost, product.getCost());
    }

    @Test
    public void getOrderProductTest() {
        Assert.assertEquals(orderProducts, product.getOrderProduct());
    }

    private static List<OrderProduct> randomOrderProductList(Long size) {
        List<OrderProduct> list = new ArrayList<OrderProduct>();
        for (int i = 0; i < size; i++) {
            list.add(new OrderProduct());
        }
        return list;
    }

}
