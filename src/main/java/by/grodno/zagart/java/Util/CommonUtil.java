package by.grodno.zagart.java.Util;

import by.grodno.zagart.java.Entities.Order;
import by.grodno.zagart.java.Entities.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Utility class.
 */
public class CommonUtil {

    public static Product createProduct() {
        Random rnd = new Random();
        Product product = new Product();
        product.setQuantity(1 + Math.abs((long) rnd.nextInt(8)));
        product.setName(RandomStringUtils.randomAlphabetic(10));
        product.setDescription(RandomStringUtils.randomAlphabetic(50));
        product.setCost(1 + Math.abs((long) rnd.nextInt(98)));
        return product;
    }

    public static List<Order> createOrders(int quantity, Session session) {
        List<Order> list = new ArrayList<Order>();
        for (int i = 0; i < quantity; i++) {
            Order order = new Order();
            order.setNumber(RandomStringUtils.randomNumeric(5));
            order.setDateOfOrder(new Date());
            Product product = createProduct();
            order.addProduct(product);
            session.save(product);
            product = createProduct();
            order.addProduct(product);
            session.save(product);
            session.save(order);
            list.add(order);
        }
        return list;
    }

}
